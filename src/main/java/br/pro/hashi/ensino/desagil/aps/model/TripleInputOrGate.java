package br.pro.hashi.ensino.desagil.aps.model;

public class TripleInputOrGate extends Gate {

    private final NandGate nandTop;
    private final NandGate nandMid1;
    private final NandGate nandBottom;
    private final NandGate nandFinal;


    public TripleInputOrGate() {
        super("TripleInputOR", 3);

        nandTop = new NandGate();
        nandMid1 = new NandGate();
        nandBottom = new NandGate();
        NandGate nandMid2 = new NandGate();
        NandGate nandMid3 = new NandGate();
        nandFinal = new NandGate();


        nandMid2.connect(0, nandTop);
        nandMid2.connect(1, nandMid1);

        nandMid3.connect(0, nandMid2);
        nandMid3.connect(1, nandMid2);

        nandFinal.connect(0, nandMid3);
        nandFinal.connect(1, nandBottom);

    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandFinal.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandTop.connect(1, emitter);
                break;
            case 1:
                nandMid1.connect(0, emitter);
                nandMid1.connect(1, emitter);

            case 2:
                nandBottom.connect(0, emitter);
                nandBottom.connect(1, emitter);
        }

    }
}
