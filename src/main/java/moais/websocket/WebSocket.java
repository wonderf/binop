package moais.websocket;

import moais.websocket.data.DataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Stas on 20.12.2016.
 */

@Controller
public class WebSocket {

    @Autowired
    private SimpMessagingTemplate template;

    private ScheduledExecutorService executor;

    //@MessageMapping("/chat*")
    //@SendTo("/topic/messages")
    //@RequestMapping("/chat*")
    public void subscribe(){
        if(executor==null){
            executor= Executors.newSingleThreadScheduledExecutor();
            executor.scheduleWithFixedDelay(this::sendData,0,10, TimeUnit.SECONDS);
        }
    }

    public void sendData() {
        this.template.convertAndSend("/topic/messages", DataCollector.getInstance().getData());
    }
}
