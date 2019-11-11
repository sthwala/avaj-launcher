package avaj.aircraft;

import	avaj.weather.*;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower _weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
				this.coordinates.setHeight(this.coordinates.getHeight() + 2);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setHeight(100);
				WriteFile.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): AHH ITS A BEAUTIFUL DAY!");
				break;
			case "RAIN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
				WriteFile.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): TURN ON THE WINDSHIELD WIPERS");
				break;
			case "FOG":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
				WriteFile.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): ITS GETTING FOGGY IM SCARED!");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				WriteFile.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): OH GOD ITS FREEZING UP HERE");
			break;
			default:
			WriteFile.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + "): THE WEATHERTOWER ISN'T RESPONDING");
			break;
		}
		if (this.coordinates.getHeight() <= 0) {
			WriteFile.getWriteFile().writetofile("Helicopter#" + this.name + "(" + this.id + ") landing.");
			WriteFile.getWriteFile().writetofile("Tower  says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		WriteFile.getWriteFile().writetofile("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		weatherTower.register(this);
	}

}