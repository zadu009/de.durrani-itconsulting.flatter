package com.hibernate.example.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hibernate.example.Game;
import com.hibernate.example.GameValue;



@Component
public class GameValueUtils {
	
	public GameValue getGameValue(Game game) {
		
		List<Game>soldGamesList = new ArrayList<Game>();
		GameValue gameValue = new GameValue();
		Integer counter = 0;
		Double sum = 0.0;
		Set<Double> priceSet = new HashSet<Double>();
		Double minPrice = null;
		Double maxPrice = null;
		int platformCode = 0; 
		String gameValueResultUrl = "";
		System.out.println("CountryCode:"+game.getCountryCode());
		System.out.println("Name:"+game.getName());
		System.out.println("Platform:"+game.getPlatform());
		Document doc;
		runCustomValidations(game);
		try {
			doc = Jsoup.connect(
					"https://www.ebay."+game.getCountryCode()+"/sch/i.html?&_nkw="+game.getName()+" "+game.getPlatform()+"&_sacat=139973&LH_TitleDesc=0&LH_Complete=1&LH_Sold=1&LH_ALL=1&LH_PrefLoc=1")
					.get();
			System.out.println("https://www.ebay."+game.getCountryCode()+"/sch/i.html?&_nkw="+game.getName()+" "+game.getPlatform()+"&_sacat=139973&LH_TitleDesc=0&LH_Complete=1&LH_Sold=1&LH_ALL=1&LH_PrefLoc=1");
			gameValueResultUrl = "https://www.ebay."+game.getCountryCode()+"/sch/i.html?&_nkw="+game.getName()+" "+game.getPlatform()+"&_sacat=139973&LH_TitleDesc=0&LH_Complete=1&LH_Sold=1&LH_ALL=1&LH_PrefLoc=1";
			Elements elementsH3 = doc.getElementsByTag("h3");
			for (Element elementH3 : elementsH3) {
				if (elementH3.hasClass("srp-save-null-search__heading")) {
					System.out.println("Suche war nicht erfolgreich");
					return null;
				}
			}

			Elements elements = doc.getElementsByTag("span");
			int gamecounter =0;
			for (Element element : elements) {
				if (element.hasClass("POSITIVE") && element.text().contains("EUR")) {
					gamecounter++;
					if (!element.hasClass("POSITIVE ITALIC")) {
						String price = element.text().substring(element.text().lastIndexOf(' ') + 1);
						price = price.replace(',', '.');
						System.out.println("Preise :" + price);
					}

				}

			}
			System.out.println("Anzahlspiele: " + gamecounter);
			
			Elements itemElements = doc.getElementsByClass("s-item");
			
			for(Element element: itemElements) {
				if(element.select("span.s-item__price > span").hasClass("POSITIVE ITALIC")){
					break;
				}
				counter = counter + 1;
				String url1 =  element.select("div.s-item__image > a").attr("href");
				String imageUrl1 =  element.select("div.s-item__image-wrapper > img").attr("src");
				String gamePrice =  element.select("span.s-item__price > span").text();
				String soldDate =  element.select("div.s-item__title--tagblock > span").text();
				String formatedDate = "";
				if(!StringUtils.isEmpty(soldDate)) {
					formatedDate = soldDate.substring(0,21);
				}else {
					continue;
				}
				Game soldGame = new Game();
				if(!gamePrice.isEmpty()) {
					String price = gamePrice.substring(gamePrice.lastIndexOf(' ') + 1);
					System.out.println("original Preis:" +price);
					price = price.replaceFirst("\\.", "");
					price = price.replace(',', '.');
					String soldgamePrice = price.replace('.', ',');
					soldGame.setPrice(soldgamePrice);
					sum += Double.valueOf(price);
					priceSet.add(Double.valueOf(price));
				}

				
				if(!url1.isBlank()) {
					soldGame.setUrl(url1);
					System.out.println("AnzeigeUrl: "+url1);
				}
				if(!imageUrl1.isBlank()) {
					soldGame.setImageUrl(imageUrl1);
				}

				if(!soldDate.isBlank()) {
					soldGame.setSoldDate(formatedDate);
				}
				
				if(soldGame != null && soldGame.getUrl() !=null) {
					soldGamesList.add(soldGame);
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		minPrice = Collections.min(priceSet);
		maxPrice = Collections.max(priceSet);
		double averagePrice = 0;
		System.out.println("Counted Games: " + counter);
		System.out.println("AveragePrice: " + sum / counter);
		System.out.println("MaxPrice: " + maxPrice);
		System.out.println("MinPrice: " + minPrice);
		System.out.println("Platform: " + game.getPlatform());
		System.out.println("CountryCode: " + game.getCountryCode() );
		System.out.println(new Date());
		averagePrice = sum / counter;
		if(averagePrice > 0 ) {
			averagePrice = getLocalAveragePrice(averagePrice, game.getCountryCode());
		}
		gameValue.setAveragePrice(averagePrice);
		soldGamesList.remove(0);
		gameValue.setGamesList(soldGamesList);
		gameValue.setNumberOfGames(counter);
		gameValue.setUrl(gameValueResultUrl);
		return gameValue;

	}
	
	private double getLocalAveragePrice(double price, String codeLocal) {
		double finalPrice = 0;
		if("com".equals(codeLocal)|| "us".equals(codeLocal)) {
			NumberFormat nfUS = NumberFormat.getNumberInstance(Locale.US);
			DecimalFormat formatterUS = (DecimalFormat) nfUS;
			formatterUS.applyPattern("###0.00");
			finalPrice = Double.valueOf(formatterUS.format(price));
		}else {
			NumberFormat nfEU = NumberFormat.getNumberInstance(Locale.GERMAN);
			DecimalFormat formatterEU = (DecimalFormat) nfEU;
			formatterEU.applyPattern("####0.00");
		    double d = Math.pow(10, 2);
		    finalPrice = Math.round(price * d) / d;
		}
		return finalPrice;		
		
	}

	private void runCustomValidations(Game game) {
		if(game.getPlatform().contains("Playstation")) {
			game.setPlatform(game.getPlatform().replaceAll("\\s", ""));
		}
		
	}

}
