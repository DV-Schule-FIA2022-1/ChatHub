package chat.workThogether.Client;

import chat.workThogether.Nachricht.ChangeMessage;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
public class EditorController
{
    @FXML private BorderPane txtEditor;
    private File loadedFileReference;
    private FileTime lastModifiedTime;
    @FXML public JFXTextArea textArea;
    public String oldText;
    private  Client client;
    private int i = 0;
    private Stack undoStack;
    private Stack redoStack;
    private Stage stage;

    public Stage getStage()
    {
        return stage;
    }
    public Client getClient()
    {
        return client;
    }
    public String getOldText() {
        return oldText;
    }
    public JFXTextArea getTextArea() {
        return textArea;
    }
    public void setClient(Client client)
    {
        this.client = client;
    }

    @FXML
    public void initialize()
    {
        undoStack = new Stack();
        redoStack = new Stack();
        System.out.println("Controller gestartet");
    }

    public void loadServerConection(int port, Stage stage)
    {
        this.stage = stage;
        client = new Client(port, this);
    }

    public void closeWindows()
    {
        client.disconnectFromServer();
    }

    public void copyToClipboard()
    {
        String text = textArea.getSelectedText();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
        System.out.println("Text '" + text + "' wurde in die Zwischenablage kopiert.");
    }

    public void pasteFromClipboard() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
            textArea.insertText(textArea.getCaretPosition(), clipboardText);
            System.out.println("Text aus der Zwischenablage: " + clipboardText);
            changeTextUpdate();
        } catch (Exception e) {
            System.out.println("Fehler beim Einfügen aus der Zwischenablage: " + e.getMessage());
        }
    }

    public void undo()
    {
        if (!undoStack.isEmpty())
        {
            redoStack.push(textArea.getText());
            textArea.setText((String) undoStack.pop());
        }
        else
        {
            System.out.println("UndoStack empty");
        }
    }

    public void redo()
    {
        if (!redoStack.isEmpty())
        {
            undoStack.push(textArea.getText());
            textArea.setText((String) redoStack.pop());
        }
        else
        {
            System.out.println("RedoStack empty");
        }
    }

    public void cutToClipboard()
    {
        copyToClipboard();

        textArea.replaceSelection("");
        changeTextUpdate();
    }

    public void delete()
    {
        textArea.replaceSelection("");
        changeTextUpdate();
    }

    public void changeTextUpdate()
    {
        if(!textArea.getText().equals(oldText))
        {
            ChangeMessage update = null;
            int[] index = findDifferenceIndexes(textArea.getText(), oldText);
            //textArea.selectRange(index[0],textArea.getLength() - index[1]);

            if(index[0] + index[1] != oldText.length())//Nochmal anschauen
            {
                update = new ChangeMessage(index[0], index[1], textArea.getText(), oldText.substring(index[0], oldText.length() - index[1]));
            }
            else
            {
                update = new ChangeMessage(index[0], index[1], textArea.getText());
            }

            try
            {
                client.schreiben(update);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            oldText = textArea.getText();
        }
    }

    public int[] findDifferenceIndexes(String newTexT, String oldText)
    {
        int minLength = Math.min(newTexT.length(), oldText.length());
        int startIndex = -1; //von forne der Index
        int endIndex = -1; //von hinten der Index
        String longText = "";
        String smallText = "";

        if (newTexT.length() > oldText.length())
        {
            longText = newTexT;
            smallText = oldText;
        }
        else if (newTexT.length() < oldText.length())
        {
            longText = oldText;
            smallText = newTexT;
        }
        else
        {
            longText = newTexT;
            smallText = oldText;
        }

        for (int i = 0; i < minLength; i++)
        {
            if (longText.charAt(i) != smallText.charAt(i))
            {
                startIndex = i;
                System.out.println("startindex gefunden");
                break;
            }
        }

        if(startIndex == -1)
        {
            startIndex = minLength;
        }

        for (int i = 1; i <= minLength; i++)
        {
            if (longText.charAt(longText.length() - i) != smallText.charAt(smallText.length() - i))
            {
                endIndex = i - 1;
                System.out.println("endindex gefunden");
                break;
            }
        }

        if(endIndex == -1)
        {
            endIndex = minLength;
        }

        if(startIndex + endIndex > smallText.length())
        {
            System.out.println("Ausgelöst");
            if(startIndex > endIndex)
            {
                endIndex = endIndex - (startIndex + endIndex - smallText.length());
            }
            else
            {
                endIndex = endIndex - (endIndex + startIndex - smallText.length());
            }
        }
//        else if(longText.length() != startIndex + endIndex ) // funktioniert noch nicht ganz, erkennt nciht den ganzen bereich beim ersetzen
//        {
//            endIndex = longText.length() - smallText.length();
//        }
        if(startIndex + endIndex != smallText.length() || smallText.length() == longText.length())
        {
            System.out.println("(element replaced)");
        }
        else if (newTexT.length() > oldText.length())
        {
            System.out.println("(element added)");
        }
        else if (newTexT.length() < oldText.length())
        {
            System.out.println("(element removed)");
        }

        System.out.println("Difference found between index " + startIndex + " and index " + endIndex + " and leangh " + smallText.length());
        return new int[]{startIndex, endIndex};
    }

    public void changedText(ChangeMessage changeMessage)
    {
        try
        {
            if(textArea.getText().substring(0, changeMessage.getStartIndex()).equals(changeMessage.getNewText().substring(0, changeMessage.getStartIndex())) &&
                    textArea.getText().substring(textArea.getText().length() - changeMessage.getEndIndex(), textArea.getText().length()).equals(changeMessage.getNewText().substring(changeMessage.getNewText().length() - changeMessage.getEndIndex(), changeMessage.getNewText().length())))
            {
                System.out.println("Text ohne wiederSpruch erkannt");
                int curserIndex = textArea.getCaretPosition();
                textArea.setText(changeMessage.getNewText());

                if(changeMessage.getStartIndex() < curserIndex)
                {
                    textArea.selectRange(curserIndex + (textArea.getLength() - changeMessage.getNewText().length()), curserIndex + (textArea.getLength() - changeMessage.getNewText().length()));
                }
                else
                {
                    textArea.selectRange(curserIndex, curserIndex);
                }
            }
            oldText = textArea.getText();
            //System.out.println("Difference found between index " + changeMessage.getStartIndex() + " and index " + changeMessage.getEndIndex());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void saveFile()
    {
        try
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save As");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showSaveDialog(null);
            if(file != null)
            {
                FileWriter myWriter = new FileWriter(file);
                myWriter.write(textArea.getText());
                myWriter.close();
                //lastModifiedTime = FileTime.fromMillis(System.currentTimeMillis() + 3000);
                System.out.println("Successfully wrote to the file.");
            }
        }
        catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(SEVERE, null, e);
        }
    }

    public void printText()
    {
        if (Printer.getDefaultPrinter() == null)
        {
            new Alert(Alert.AlertType.ERROR,"No default printer has been selected").showAndWait();
            return;
        }
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null)
        {
            if(printerJob.showPageSetupDialog(txtEditor.getScene().getWindow()))
            {
                if (printerJob.printPage(textArea))
                {
                    printerJob.endJob();
                }
                else
                {
                    new Alert(Alert.AlertType.ERROR,"Failed to print, try again").showAndWait();
                }
            }
        }
        else
        {
            new Alert(Alert.AlertType.ERROR,"Failed to initialize a new printer job").show();
        }
    }

    public void selectAll()
    {
        textArea.selectRange(0, textArea.getLength());
    }

    public void search()
    {
        Stage sucheStage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,400,100);
        HBox textBox = new HBox(4);
        textBox.setAlignment(Pos.BOTTOM_CENTER);
        textBox.getChildren().add(new Label("Suche nach"));
        TextField stext = new TextField("");
        textBox.getChildren().add(stext);
        root.setTop(textBox);
        CheckBox checkBox = new CheckBox("Groß- / Kleinschreibung beachten");
        root.setCenter(checkBox);
        HBox bBox = new HBox(4);
        Button bsuche = new Button("Suche");
        Label eLabel = new Label("");
        List<Integer> fList = new ArrayList<Integer>(1);

        bsuche.setOnAction(new EventHandler<ActionEvent>()
           {
               @Override
               public void handle(ActionEvent e) {
                   asuche(checkBox,fList,textArea,stext,eLabel);
               }
           }
        );

        Button bnext = new Button("Nächstes");
        bnext.setOnAction(new EventHandler<ActionEvent>()
              {
                  @Override
                  public void handle(ActionEvent e) {
                      if(i == fList.size()-1){
                          i=0;
                      }else{
                          i ++;
                      }
                      textArea.selectRange(fList.get(i), fList.get(i)+ stext.getText().length());

                  }
              }
        );

        Button bPrevious  = new Button("Vorher");
        bPrevious.setOnAction(new EventHandler<ActionEvent>()
          {
              @Override
              public void handle(ActionEvent e)
              {
                  if(i == 0)
                  {
                      i=fList.size()-1;
                  }else
                  {
                      i --;
                  }
                  textArea.selectRange(fList.get(i), fList.get(i)+ stext.getText().length());
              }
          }
        );

        bBox.getChildren().add(bsuche);
        bBox.getChildren().add(bPrevious);
        bBox.getChildren().add(bnext);
        bBox.getChildren().add(eLabel);
        root.setBottom(bBox);
        sucheStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        sucheStage.show();
    };

    public void replaceSearch()
    {
        Stage sucheStage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,400,150);
        VBox textboxV = new VBox(2);
        HBox textBox1 = new HBox(4);
        textBox1.setAlignment(Pos.BOTTOM_CENTER);
        textBox1.getChildren().add(new Label("Suche nach"));
        TextField stext = new TextField("");
        textBox1.getChildren().add(stext);
        HBox textBox2 = new HBox(4);
        textBox2.setAlignment(Pos.BOTTOM_CENTER);
        textBox2.getChildren().add(new Label("Ersetz Mit"));
        TextField stext2 = new TextField("");
        textBox2.getChildren().add(stext2);
        textboxV.getChildren().add(textBox1);
        textboxV.getChildren().add(textBox2);
        root.setTop(textboxV);
        CheckBox checkBox = new CheckBox("Groß- / Kleinschreibung beachten");
        root.setCenter(checkBox);
        HBox bBox = new HBox(4);
        Button bsuche = new Button("Suche");
        Label eLabel = new Label("");
        List<Integer> fList = new ArrayList<Integer>(1);

        bsuche.setOnAction(new EventHandler<ActionEvent>()
           {
               @Override
               public void handle(ActionEvent e) {
                   asuche(checkBox,fList,textArea,stext,eLabel);
               }
           }
        );

        Button bnext = new Button("Nächstes");
        bnext.setOnAction(new EventHandler<ActionEvent>()
          {
              @Override
              public void handle(ActionEvent e)
              {
                  if(fList.size() != 0)
                  {
                      if(i == fList.size()-1)
                      {
                          i=0;
                      }
                      else{
                          i ++;
                      }
                      textArea.selectRange(fList.get(i), fList.get(i)+ stext.getText().length());

                  }}
          }
        );

        Button bPrevious  = new Button("Vorher");
        bPrevious.setOnAction(new EventHandler<ActionEvent>()
          {
              @Override
              public void handle(ActionEvent e) {
                  if(fList.size() != 0){

                      if(i == 0){
                          i=fList.size()-1;
                      }else
                      {
                          i --;
                      }
                      textArea.selectRange(fList.get(i), fList.get(i)+ stext.getText().length());

                  }
              }
          }
        );

        Button replace   = new Button("ersetzen");
        replace.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent e)
                {
                    System.out.println(fList.size());
                    System.out.println(i);
                    for(int i:fList)
                    {
                        System.out.println(i);
                    }
                    if(fList.size() != 0)
                    {
                        textArea.replaceText(fList.get(i), fList.get(i)+ stext.getText().length(),stext2.getText());
                        fList.remove(i);
                        if(fList.size() != 0)
                        {
                            checkindex(checkBox,fList,textArea,stext);
                        }}
                    if(fList.size() != 0)
                    {
                        if(i >= fList.size())
                        {
                            i = fList.size() -1 ;
                        }
                        textArea.selectRange(fList.get(i), fList.get(i)+ stext.getText().length());
                    }
                    else
                    {
                        textArea.selectRange(0, 0);
                    }

                    changeTextUpdate();
                }
            }
        );

        Button replaceAll  = new Button("alles ersetzen");
        replaceAll.setOnAction(new EventHandler<ActionEvent>()
           {
               @Override
               public void handle(ActionEvent e) {
                   asuche(checkBox,fList,textArea,stext,eLabel);
                   if(fList.size() != 0){
                       if(checkBox.isSelected()){
                           textArea.setText(textArea.getText().replaceAll("(?i)"+stext.getText(), stext2.getText()));
                       }else{
                           textArea.setText(textArea.getText().replaceAll(stext.getText(), stext2.getText()));
                       }
                       fList.clear();
                       textArea.selectRange(0, 0);
                   }

                   changeTextUpdate();
               }
           }
        );


        bBox.getChildren().add(bsuche);
        bBox.getChildren().add(bPrevious);
        bBox.getChildren().add(bnext);
        bBox.getChildren().add(replace);
        bBox.getChildren().add(replaceAll);
        VBox bBoxV = new VBox(2);
        bBoxV.getChildren().add(eLabel);
        bBoxV.getChildren().add(bBox);
        root.setBottom(bBoxV);
        sucheStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        sucheStage.show();
    };

    public void checkindex(CheckBox checkBox,List fList,JFXTextArea fullText,TextField stext){
        if(checkBox.isSelected())
        {
            fList.clear();
            int x = fullText.getText().toLowerCase().indexOf(stext.getText().toLowerCase());
            fList.add(x);
            while(x >= 0)
            {
                x = fullText.getText().toLowerCase().indexOf(stext.getText().toLowerCase(), x+1);
                if (x != -1)
                {
                    fList.add(x);
                }
            }
        }else
        {
            fList.clear();
            int x = fullText.getText().indexOf(stext.getText());
            fList.add(x);
            while(x >= 0)
            {
                x = fullText.getText().indexOf(stext.getText(), x+1);
                if (x != -1)
                {
                    fList.add(x);
                }
            }
        }

    }


    public void asuche(CheckBox checkBox,List<Integer> fList,JFXTextArea fullText,TextField stext,Label eLabel)
    {
        fList.clear();
        i = 0;
        if(stext.getText().toLowerCase() != null && !stext.getText().isEmpty())
        {
            if(checkBox.isSelected())
            {
                int index = fullText.getText().toLowerCase().indexOf(stext.getText().toLowerCase());
                if (index == -1)
                {
                    eLabel.setText("Search key Not in the text");
                } else
                {
                    int x = fullText.getText().toLowerCase().indexOf(stext.getText().toLowerCase());
                    fList.add(x);
                    while (x >= 0)
                    {
                        x = fullText.getText().toLowerCase().indexOf(stext.getText().toLowerCase(), x + 1);
                        if (x != -1)
                        {
                            fList.add(x);
                        }
                    }
                    eLabel.setText("Gefunden");
                    i = 0;
                    fullText.selectRange(fList.get(i), fList.get(i) + stext.getText().toLowerCase().length());
                }
            }
            else
            {
                int index = fullText.getText().indexOf(stext.getText());
                if (index == -1)
                {
                    eLabel.setText("Suchbegriff Nicht im Text");
                }
                else
                {
                    int x = fullText.getText().indexOf(stext.getText());
                    fList.add(x);
                    while (x >= 0)
                    {
                        x = fullText.getText().indexOf(stext.getText(), x + 1);
                        if (x != -1)
                        {
                            fList.add(x);
                        }
                    }
                    eLabel.setText("Found");
                    i = 0;
                    fullText.selectRange(fList.get(i), fList.get(i) + stext.getText().length());
                }
            }
        }
        else
        {
            eLabel.setText("Fehlender Suchbegriff");
            //  errorText.setFill(Color.RED);
        }
    }
}