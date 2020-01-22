#include <Keypad.h>
#include <Stepper.h>

int index;
char key;
char unlockPassword[4] = {'0', '8', '2', '4'};
char lockPassword[4] = {'1', '9', '9', '8'};
bool unlock;
bool lock;
const int stepsPerRevolution = 512;
const int rolePerMinute = 45;
const int ROWS = 4;
const int COLS = 4;

char keys[ROWS][COLS] =
{
  {'1', '2','3', 'A'},
  {'4', '5','6', 'B'},
  {'7', '8','9', 'C'},
  {'*', '0','#', 'D'}
};
byte rowPins[ROWS] = {2,3,4,5};
byte colPins[COLS] = {6,7,8,9};

Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);
Stepper myStepper(stepsPerRevolution, 10, 11, 12, 13);

void setup()
{
  myStepper.setSpeed(rolePerMinute);
  Serial.begin(9600);
}

void loop()
{
  char input[4];
  Serial.print("Ready !!! ");
  while (index != 4)
  {
    key = keypad.getKey();
    if (key != NO_KEY)
    {
      input[index] = key;
      Serial.print("\ninput ");
      Serial.print(index);
      Serial.print(" : ");
      Serial.print(input[index]);
      index++;
    }
  }

  for (int i = 0; i < 4; i++)
  {
    if (input[i] == unlockPassword[i])
    {
      unlock = true;
    }
    else
    {
      unlock = false;
      break;
    }
  }
  for (int i = 0; i < 4; i++)
  {
    if (input[i] == lockPassword[i])
    {
      lock = true;
    }
    else
    {
      lock = false;
      break;
    }
  }

  if (unlock)
  {
    Serial.println("\nUnlocking");
    myStepper.step(stepsPerRevolution);
    delay(500);
  }
  else if(lock)
  {
    Serial.println("\nLocking");
    myStepper.step(-stepsPerRevolution);
    delay(500);
  }
  else
  {
    Serial.print("\nIncorrect Password !!");
    delay(1000);
  }
}
