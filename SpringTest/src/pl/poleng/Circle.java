package pl.poleng;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Shape, ApplicationEventPublisherAware{

	   
	private Point center;
	private ApplicationEventPublisher publisher;
	@Autowired
	private MessageSource massageSource;

	public MessageSource getMassageSource() {
		return massageSource;
	}

	public void setMassageSource(MessageSource massageSource) {
		this.massageSource = massageSource;
	}

	public Point getCenter() {
		return center;
	}

	@Resource(name = "pointA")
	public void setCenter(Point center) {
		this.center = center;
	}

	@Override
	public void draw() {
		System.out.println(massageSource.getMessage("greeting",null, "Default greeting",null));
		System.out.println(massageSource.getMessage("drawing.message", new Object[] {getCenter().getX(), getCenter().getY()}, "Default",null));
		DrawEvent event = new DrawEvent(this);
		publisher.publishEvent(event);
		
	}

	@PostConstruct
	public void initalizeCircle() {
		System.out.println("Cirlce inicialized");
	}

	@PreDestroy
	public void destroyCircle() {
		System.out.println("Destroy circle 1");

	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
		
	}

}
