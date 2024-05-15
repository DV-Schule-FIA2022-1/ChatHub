package chat.Test;

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
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
public class EditorController
{
    @FXML private BorderPane txtEditor;
    private File loadedFileReference;
    private FileTime lastModifiedTime;
    @FXML public JFXTextArea textArea;
    public int i = 0;

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

        Button bnext = new Button("Next");
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

        Button bPrevious  = new Button("Previous");
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


        Button bnext = new Button("Next");
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