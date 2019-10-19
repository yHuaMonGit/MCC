package com.youga.mcc.task;


import com.youga.mcc.obj.OrderNotesBase;
import com.youga.mcc.service.OrderService;
import com.youga.mcc.service.impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderTask {


    private final Logger logger = LoggerFactory.getLogger(OrderTask.class);

    OrderService orderService = new OrderServiceImpl();

    @Scheduled(cron = "0/30 * * * * ? ")
    public void taskCycle() throws Exception {

        //logger.info("check timeout order info - [nop]");

        List<OrderNotesBase> orderBaseList = orderService.checkTimeOutOrder();
        if (null != orderBaseList){
            for (OrderNotesBase order:orderBaseList)
            {
                orderService.deleteOrder(order.getOrder_no());
                logger.info("check timeout order info - [order_no:"+order.getOrder_no()+
                        ",member_id:"+order.getMember_id()+
                        ",total_amount:"+order.getOrder_amount_total()+
                        "]" );
            }

        }

    }


}
