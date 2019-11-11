package avaj.aircraft;

import	avaj.weather.*;
import	avaj.aircraft.*;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower _weatherTower;
	
	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
				this.coordinates.setHeight(this.coordinates.getHeight() + 4);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setLongitude(100);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): GOOD THING I BROUGHT MY SUNGLASSES");
				break;
			case "RAIN":
				this.coordinates.setHeight(this.coordinates.getHeight() - 5);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): I WISH THIS BALLON WASN'T OPEN");
				break;
			case "FOG":
				this.coordinates.setHeight(this.coordinates.getHeight() - 2);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): I CAN'T SEE!!!");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 15);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): IM STARTING TO FREEZE UP HERE!");
				WriteFile.getWriteFile().writetofile("Tower  says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
				break;
			default:
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): I CANT CONTACT THE WEATHER TOWER");
				break;
		}
		if (this.coordinates.getHeight() <= 0){
			WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + ") landing.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		WriteFile.getWriteFile().writetofile("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		weatherTower.register(this);
	}
}