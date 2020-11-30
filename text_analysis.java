import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.beans.value.*;
public class Homework8 extends Application
{
	Stage window;
	Scene scene;

	public static void main(String[] args) 
	{
		launch(args);

	}
	
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		
		RadioButton small = new RadioButton("Small");
		RadioButton medium = new RadioButton("Medium");
		RadioButton large = new RadioButton("Large");
		
		ToggleGroup radioGroup = new ToggleGroup();
		
		small.setToggleGroup(radioGroup);
		medium.setToggleGroup(radioGroup);
		large.setToggleGroup(radioGroup);
		
		small.setUserData("Small");
		medium.setUserData("Medium");
		large.setUserData("Large");
		
		TextArea textArea = new TextArea();
		textArea.setMinHeight(450);
		textArea.setMaxWidth(240);
		
		radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) 
			{
				if(radioGroup.getSelectedToggle() == small)
				{
					textArea.setStyle("-fx-font-size: 12");;
				}
				else if(radioGroup.getSelectedToggle() == medium)
				{
					textArea.setStyle("-fx-font-size: 22");;
				}
				else if(radioGroup.getSelectedToggle() == large)
				{
					textArea.setStyle("-fx-font-size: 32");;
				}
			}
			
		});
		
		TextField textField = new TextField();
		textField.setMinHeight(450);
		textField.setMaxWidth(260);
		textField.setDisable(true);
		
		String[] type = new String[] {"Word Count","Char Count","Vowel Count"};
		CheckBox[] cbs = new CheckBox[type.length];
		for (int i = 0; i < type.length; i++)
		{
			CheckBox cb = cbs[i] = new CheckBox(type[i]);
			cb.selectedProperty().addListener((ChangeListener<Boolean>) (ov, old_val, new_val) -> 
			{
				String result = "";
				if(cbs[0].isSelected())
				{
					result += wordCount(textArea);
				}
				if(cbs[1].isSelected())
				{
					result += charCount(textArea);
				}
				if(cbs[2].isSelected())
				{
					result+= vowelCount(textArea);
				}
				textField.setText(result);
				
			});
		}
		
		String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 1;\n" +
                "-fx-border-style: solid;\n";
		HBox sizeButtons = new HBox(small, medium, large);
		sizeButtons.setStyle(cssLayout);
		Label text = new Label();
		text.setText("TextArea");
		Label size = new Label();
		size.setText("Size");
		StackPane pane1 = new StackPane();
		pane1.getChildren().addAll(size,sizeButtons);
		
		VBox vbox1 = new VBox(text, textArea, size, pane1);
		vbox1.setSpacing(5);
		vbox1.setStyle(cssLayout);
		
		HBox analysisTypes = new HBox();
		analysisTypes.getChildren().addAll(cbs);
		analysisTypes.setStyle(cssLayout);
		Label textAnalysis = new Label();
		textAnalysis.setText("Text Analysis");
		Label analysisType = new Label();
		analysisType.setText("Analysis Types");
		StackPane pane2 = new StackPane();
		pane2.getChildren().addAll(textAnalysis, analysisTypes);
		
		VBox vbox2 = new VBox(textAnalysis, textField, analysisType, pane2);
		vbox2.setSpacing(5);
		vbox2.setStyle(cssLayout);
		
		HBox total = new HBox(vbox1,vbox2);
		
		scene = new Scene(total, 540,600);
		window.setScene(scene);
		window.show();
	}
	
	public static String wordCount(TextArea textArea)
	{
		String result;
		String text = textArea.getText();
		char[] chars = text.toCharArray();
		int count = 1;
		for(int i = 0; i < chars.length; i++)
		{
			if(chars[i] == ' ')
			{
				count++;
			}
		}
		if(chars[0] == ' ')
			count--;
		result = "Word Count: " + count + "\n";
		return result;
	}
	
	public static String charCount(TextArea textArea)
	{
		String result;
		String text = textArea.getText();
		System.out.println(text.length());
		result = "Char Count: " + text.length() + "\n";
		return result;
	}
	public static String vowelCount(TextArea textArea)
	{
		String result;
		String text = textArea.getText();
		char[] chars = text.toCharArray();
		int count = 0;
		for(int i = 0; i < chars.length; i++)
		{
			
			if(chars[i] == 'a' || chars[i] == 'A' || chars[i] == 'e' 
			|| chars[i] =='E' ||chars[i] == 'i'|| chars[i] == 'I'
			||chars[i] == 'o'|| chars[i] == 'O'|| chars[i] == 'u'||chars[i] == 'U')
			{
				count++;
			}
		}
		
		result = "Vowel Count: " + count + "\n";
		return result;
	}

}
