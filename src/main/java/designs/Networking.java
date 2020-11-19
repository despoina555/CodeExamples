package designs;

public enum Networking {

    INSTAGRAM,LINKEDIN, TINDER,
    PHONE(ConnectType.LANDLINE), SKYPE(ConnectType.VoIP);

    private final ConnectType connectType;
    Networking() { this.connectType= ConnectType.TEXT; }
    Networking(ConnectType connectType) {
        this.connectType=connectType;
    }

    void connect(){
        connectType.connect();
    }

    private enum ConnectType {

        TEXT {
            @Override
            void connect() {
                System.out.print("Send a PM \n ");
            }
        },

        VoIP {
            @Override
            void connect() {
                System.out.print("Call her using wifi! ");
            }
        },

        LANDLINE {
            @Override
            void connect() {
                System.out.print("Vintage call! ");
            }
        };

        abstract void connect();
    }
}