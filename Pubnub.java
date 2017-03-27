import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.*;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.*;
import com.pubnub.api.models.consumer.pubsub.*;
import java.util.Arrays;

public class PubnubUtil {

    private static PubNub pubnub;

    public static void main(String[] args) {

        init();

    }

    public static void init() {
        System.out.println("Starting pubnub");
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-2dd510a2-1209-11e7-8403-0619f8945a4f");
        pnConfiguration.setPublishKey("pub-c-180ed609-5056-42f7-8aba-ad47e2165711");
        pnConfiguration.setSecure(false);

        pubnub = new PubNub(pnConfiguration);


        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {


                if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {
                    // This event happens when radio / connectivity is lost
                }

                else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {

                    // Connect event. You can do stuff like publish, and know you'll get it.
                    // Or just use the connected event to confirm you are subscribed for
                    // UI / internal notifications, etc

                    if (status.getCategory() == PNStatusCategory.PNConnectedCategory){
                        pubnub.publish().channel("awesomeChannel").message("hello!!").async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                // Check whether request successfully completed or not.
                                if (!status.isError()) {

                                    // Message successfully published to specified channel.
                                }
                                // Request processing failed.
                                else {

                                    // Handle message publish error. Check 'category' property to find out possible issue
                                    // because of which request did fail.
                                    //
                                    // Request can be resent using: [status retry];
                                }
                            }
                        });
                    }
                }
                else if (status.getCategory() == PNStatusCategory.PNReconnectedCategory) {

                    // Happens as part of our regular operation. This event happens when
                    // radio / connectivity is lost, then regained.
                }
                else if (status.getCategory() == PNStatusCategory.PNDecryptionErrorCategory) {

                    // Handle messsage decryption error. Probably client configured to
                    // encrypt messages and on live data feed it received plain text.
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                // Handle new message stored in message.message
                System.out.println(message.getMessage().toString());
                SmartHouse.process(message.getMessage().toString());
                if (message.getChannel() != null) {
                    // Message has been received on channel group stored in
                    // message.getChannel()
                }
                else {
                    // Message has been received on channel stored in
                    // message.getSubscription()
                }

            /*
                log the following items with your favorite logger
                    - message.getMessage()
                    - message.getSubscription()
                    - message.getTimetoken()
            */
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });

        pubnub.subscribe().channels(Arrays.asList("test")).execute();
        System.out.println("Finished pubnub");
    }


}
