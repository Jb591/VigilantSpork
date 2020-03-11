.SUFFIXES: .java .class

.java.class:
	javac -g $*.java

CLASSES = \
	Background.java \
	Bank.java \
	Product.java \
	Choice.java \
	Main.java

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: Main.class
	java Main

clean:
	rm -f *.class
