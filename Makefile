sdes:
	javac SDES.java
	java SDES
	make clean

triple-sdes:
	javac TripleSDES.java
	java TripleSDES
	make clean

clean:
	rm -rf SDES.class TripleSDES.class
	rm -rf CASCII.class CASCII.class