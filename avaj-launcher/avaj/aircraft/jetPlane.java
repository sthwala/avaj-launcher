package avaj.aircraft;

import	avaj.weather.*;

public class jetPlane extends Aircraft implements Flyable {
	private WeatherTower _weatherTower;

	jetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);		
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
				this.coordinates.setHeight(this.coordinates.getHeight() + 2);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setHeight(100);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): AHH THE SUN IS BLINDING ME!");
				break;
			case "RAIN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): WHO NEEDS WINDSHELD WIPERS FOR RAIN AT THIS SPEED HAHA");
				break;
			case "FOG":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): CRAP I CANT SEE ANYTHING IN THIS FOG!");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): THE SNOW IS FREEZING MY WINDOWS THEY ARE GOING TO CRACK");
				break;
			default:
			WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): ALL HOPE IS LOST I CAN't COUNTACT THE WEATHER TOWER");
			break;
		}
		if (this.coordinates.getHeight() <= 0) {
			WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + ") landing.");
			WriteFile.getWriteFile().writetofile("Tower  says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		WriteFile.getWriteFile().writetofile("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		this._weatherTower.register(this);
	}
	
}