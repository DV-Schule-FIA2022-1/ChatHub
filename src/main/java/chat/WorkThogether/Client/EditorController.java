package chat.WorkThogether.Client;

import chat.WorkThogether.Nachricht.ChangeMessage;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    public int i = 0;
    private Stack undo;
    private Stack redo;
    @FXML
    public void initialize()
    {
        oldText = textArea.getText();

//        textArea.textProperty().addListener(new ChangeListener
//                () {
//            @Override
//            public void changed(ObservableValue observable, String oldValue, String newValue) {
//                changeTextUpdate(newValue);
//            }
//        });
//        textArea.setOnInputMethodTextChanged(event -> {
//            changeTextUpdate(event.getCommitted());
//        });

        try
        {
            client = new Client(1234, this);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("Controller gestartet");
    }

    public void changeTextUpdate()
    {
        if(!textArea.getText().equals(oldText))
        {
            //System.out.println(i++);
            //ChangeMessage update = new ChangeMessage();
            ArrayList<int[]> index = findDifferenceIndexes(textArea.getText(), oldText);
            //detectChanges(oldText, textArea.getText());
            //textArea.selectRange(index[0],index[1]);
            oldText = textArea.getText();
        }
    }

    public ArrayList<int[]> findDifferenceIndexes(String newTexT, String oldText)
    {
        ArrayList<int[]> arrayList = new ArrayList<int[]>();
        int minLength = Math.min(newTexT.length(), oldText.length());
        int startIndex = 0;
        int endIndex = 0;
        String longText = "";
        String smallText = "";

        if (newTexT.length() > oldText.length())
        {
            System.out.println("(element added)");
            longText = newTexT;
            smallText = oldText;

            //endIndex = textArea.getCaretPosition();
            //startIndex = endIndex - (newTexT.length() - oldText.length());
        }
        else if (newTexT.length() < oldText.length())
        {
            System.out.println("(element removed)");
            longText = oldText;
            smallText = newTexT;

            //startIndex = textArea.getCaretPosition();
            //endIndex = startIndex + (oldText.length() - newTexT.length());
        }
        else
        {
            System.out.println("(element replaced)");
            longText = newTexT;
            smallText = oldText;
            //endIndex = textArea.getCaretPosition();

        }

        //startIndex = smallText.length();

        for (int index = 0; index < minLength; index++)
        {
            if (longText.charAt(index + (endIndex - startIndex)) != smallText.charAt(index))
            {
                startIndex = index;
                System.out.println("startindex gefunden");

                //endIndex = textArea.getCaretPosition(); //hinzufügen, das mit dem Cursor herausgefunden wird in einer reihe gleicher buchstaben welcher verändert wurde.
                for (int i = startIndex; i <= minLength; i++)
                {
                    if (smallText.charAt(index) == longText.charAt(i))
                    {
                        endIndex = i;
                        System.out.println("endindex gefunden");
                        index = i;
                        arrayList.add(new int[]{startIndex, endIndex});
                        break;
                    }
                }
            }
        }
        if(endIndex == 0)endIndex = longText.length();

        System.out.println("Difference found between index " + startIndex + " and index " + endIndex);
        return arrayList;
    }

    public static void detectChanges(String originalText, String modifiedText) {
        int[][] dp = new int[originalText.length() + 1][modifiedText.length() + 1];

        for (int i = 0; i <= originalText.length(); i++) {
            for (int j = 0; j <= modifiedText.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (originalText.charAt(i - 1) == modifiedText.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        int i = originalText.length();
        int j = modifiedText.length();

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 && originalText.charAt(i - 1) == modifiedText.charAt(j - 1)) {
                i--;
                j--;
            } else if (j > 0 && dp[i][j] == dp[i][j - 1] + 1) {
                System.out.println("Inserted: " + modifiedText.charAt(j - 1));
                j--;
            } else if (i > 0 && dp[i][j] == dp[i - 1][j] + 1) {
                System.out.println("Deleted: " + originalText.charAt(i - 1));
                i--;
            } else if (i > 0 && j > 0 && dp[i][j] == dp[i - 1][j - 1] + 1) {
                System.out.println("Replaced: " + originalText.charAt(i - 1) + " with " + modifiedText.charAt(j - 1));
                i--;
                j--;
            }
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
            printerJob.showPageSetupDialog(txtEditor.getScene().getWindow());
            if (printerJob.printPage(textArea))
            {
                printerJob.endJob();
            }
            else
            {
                new Alert(Alert.AlertType.ERROR,"Failed to print, try again").showAndWait();
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