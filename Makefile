sdes:
	javac SDES.java
	java SDES
	make clean

triple-sdes:
	javac TripleSDES.java
	java TripleSDES
	make clean

crack-sdes:
	javac CrackSDES.java
	java CrackSDES
	make clean

crack-triple-sdes:
	javac CrackTripleSDES.java
	java CrackTripleSDES
	make clean

clean:
	rm -rf SDES.class TripleSDES.class CrackSDES.class CrackTripleSDES.class
	rm -rf CASCII.class CASCII.class