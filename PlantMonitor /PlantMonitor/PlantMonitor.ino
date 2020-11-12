#include <DHT.h>
#include <LiquidCrystal.h>
#define DHTPIN 8 // pin connection
#define DHTTYPE DHT11 // Type of DHT
DHT dht(DHTPIN, DHTTYPE); // creating object
const int RS = 12, E = 11, D4 = 5, D5 = 4, D6 = 3, D7 = 2;
const int pushButton = 9;
int readButton = 3;
int oldIndex = 0;
int newIndex = 0;
LiquidCrystal lcd(RS, E, D4, D5, D6, D7);

void setup()
{
  dht.begin();
  lcd.begin(16, 2);
  pinMode(pushButton, INPUT);
}

void loop()
{
  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();
  temperature = (temperature * 9) / 5 + 32;
  readButton = digitalRead(pushButton);
  if (readButton == HIGH && newIndex == 0)
  {
    newIndex++;
  }
  else if (readButton == HIGH && newIndex != oldIndex)
  {
    newIndex = oldIndex;
  }
  if(newIndex != oldIndex)
  {
    if (isnan(temperature) && isnan(humidity))
    {
      lcd.setCursor(0,0);
      lcd.print("Failed to Read From DHT");
    }
    if (humidity >= 35 && humidity <= 55)
    {
      lcd.setCursor(0,0);
      lcd.print("Humid:");
      lcd.print(humidity);
      lcd.print("%");
      lcd.setCursor(12,0);
      lcd.print("GOOD");
    }
    else
    {
      lcd.setCursor(0,0);
      lcd.print("Humid:");
      lcd.print(humidity);
      lcd.print("%");
      lcd.setCursor(13,0);
      lcd.print("BAD");
    }
    if (temperature >= 64 && temperature <= 78)
    {
      lcd.setCursor(0,1);
      lcd.print("Temp:");
      lcd.print(temperature);
      lcd.print("F");
      lcd.setCursor(12,1);
      lcd.print("GOOD");
    }
    else
    {
      lcd.setCursor(0,1);
      lcd.print("Temp:");
      lcd.print(temperature);
      lcd.print(" F");
      lcd.setCursor(13,1);
      lcd.print("BAD");
    }
    delay(100);
  }
  else if (newIndex == oldIndex)
  {
    float soilSensor = analogRead(A0);
    soilSensor = soilSensor / 10;
    lcd.clear();
    lcd.setCursor(0,0);
    // Soil Sensor Code goes Here
    if (soilSensor >= 0 && soilSensor < 30)
    {
      lcd.print("Moist:");
      lcd.print(soilSensor);
      lcd.print("%");
      lcd.setCursor(6,1);
      lcd.print("Dry");
    }
    else if (soilSensor >= 30 && soilSensor < 70)
    {
      lcd.print("Moist:");
      lcd.print(soilSensor);
      lcd.print("%");
      lcd.setCursor(5,1);
      lcd.print("GOOD");
    }
    else
    {
      lcd.print("Moist:");
      lcd.print(soilSensor, 1);
      lcd.print("%");
      lcd.setCursor(4,1);
      lcd.print("Too Wet");
    }
    delay(100);
  }
}
