package student;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PersistenceContext
    private EntityManager entityManager;
//    @Autowired
//    private SessionFactory sessionFactory;
    
    @PostMapping
    @RequestMapping
    public ResponseEntity processTacoOrder(@RequestBody Order order) throws UnknownHostException {
        
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderType(order.getOrderType());
        orderEntity.setOrderQuantity(order.getQuantity());
        
        InetAddress localHost = InetAddress.getLocalHost();
        
//        try {
//            Class.forName("net.sf.log4jdbc.DriverSpy");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        Transaction transaction = null;
        try (Session session = (Session) entityManager.getDelegate()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(orderEntity);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
//      log.info("Processing taco " + taco);
        
        order.setId(orderEntity.getOrderId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
