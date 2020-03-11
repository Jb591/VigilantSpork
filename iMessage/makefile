.SUFFIXES: .java .class

.java.class:
	javac -g $*.java

CLASSES = \
	AddUser.java \
	IMessage.java \
	Main.java \
	MessageBox.java 

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: Main.class
	java Main

clean:
	rm -f *.class
