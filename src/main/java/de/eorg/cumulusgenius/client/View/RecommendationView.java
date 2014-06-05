package de.eorg.cumulusgenius.client.View;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

import de.eorg.cumulusgenius.client.CumulusGenius;
import de.eorg.cumulusgenius.client.View.Handlers.ViewHandler;

public class RecommendationView extends AbstractView implements EntryPoint {

	public RecommendationView() {
		setHeading(new HTML("<h1>Recommendation</h1>", true));
		setInstructions(new HTML("<h2>This is the best known solution!</h2>",
				true));
		
		
		
		// Back und Next Button bauen
		IButton backButton = new IButton("Back");
		backButton.setTop(100);

		IButton nextButton = new IButton("Finish");
		nextButton.setTop(100);
		nextButton.setLeft(220);

		backButton.addClickHandler(new ViewHandler(CumulusGenius.preferencesView));
		nextButton.addClickHandler(new ViewHandler(CumulusGenius.recommendationView));

		HLayout buttonLayout = new HLayout();
		buttonLayout.setHeight(150);
		buttonLayout.setMembersMargin(5);
		buttonLayout.setLayoutMargin(10);
		buttonLayout.addChild(backButton);
		buttonLayout.addChild(nextButton);

		// Alles auf in Layout packen
		getLayout().add(getHeading());
		getLayout().add(getInstructions());
		onModuleLoad();
		getLayout().add(buttonLayout);
		
		

	}

	@Override
	public void onModuleLoad() {
		TileGrid tileGrid = new TileGrid();  
        tileGrid.setTileWidth(194);  
        tileGrid.setTileHeight(165);  
        tileGrid.setHeight(400);  
        tileGrid.setWidth100();  
        tileGrid.setCanReorderTiles(false);  
        tileGrid.setShowAllRecords(true);  
       // tileGrid.setData(ProviderData.getRecords());
        
        DetailViewerField pictureField = new DetailViewerField("picture");  
        pictureField.setType("image");  
        //pictureField.setImageURLPrefix("providers/");  
        pictureField.setImageWidth(186);  
        pictureField.setImageHeight(120);  
  
        DetailViewerField nameField = new DetailViewerField("name");  
        DetailViewerField priceField = new DetailViewerField("price");  
  
        tileGrid.setFields(pictureField, nameField, priceField);  
  
        tileGrid.draw();
        getLayout().add(tileGrid);
	}

	




}
