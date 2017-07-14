package moais.home;

import java.security.Principal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import moais.websocket.data.DataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	@Autowired
	private SimpMessagingTemplate template;
    private ScheduledExecutorService executor;

    @MessageMapping("/chat*")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal) {
        if (principal==null)
            return "home/homeNotSignedIn";
        if(executor==null){
            executor= Executors.newSingleThreadScheduledExecutor();
            executor.schedule(this::sendHistoryData,0,TimeUnit.SECONDS);
            executor.scheduleWithFixedDelay(this::sendData,0,10, TimeUnit.SECONDS);
        }
        return "home/homeSignedIn";
	}
    public void sendData() {
        this.template.convertAndSend("/topic/messages", DataCollector.getInstance().getData());
    }

    public void sendHistoryData(){
        this.template.convertAndSend("/topic/messages", DataCollector.getInstance().getLastWeekData());
    }
}
