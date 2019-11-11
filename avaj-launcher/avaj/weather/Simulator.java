package avaj.weather;

import avaj.aircraft.*;
import avaj.weather.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public	class	Simulator {
	private static WeatherTower weatherTower;
	private static List<Flyable> flyables = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		try {
			if(args.length < 1)
				return;
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			String line = reader.readLine();
			if (line != null) {
				weatherTower = new WeatherTower();
				int simulations = Integer.parseInt(line);
				if (simulations < 0) {
					System.out.println("Invalid simulations count " + simulations);
					System.exit(1);
				}
				Flyable flyable;
				while ((line = reader.readLine()) != null) {
					flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
						Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
						Integer.parseInt(line.split(" ")[4]));
					if (flyable != null)
						flyables.add(flyable);
				}

				for (Flyable flyable1 : flyables) {
					flyable1.registerTower(weatherTower);
				}

				// for(int i = 0; i < 1; i++)
				// 	flyables.get(i).registerTower(weatherTower);

				for (int i = 1; i <= simulations; i++) {
					weatherTower.changeWeather();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find file " + args[0]);
		} catch (IOException e) {
			System.out.println("There was an error while reading the file " + args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Specify simulation file");
		} catch (NullPointerException e) {
			System.out.println("value is null");
		} catch (NumberFormatException e) {
			System.out.println("not a valid number entered in file");
		} finally {
			WriteFile.getWriteFile().close();
		}
	}
}
