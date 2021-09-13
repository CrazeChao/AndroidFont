package com.style.font;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by lizhichao on 2021/9/8
 */
public class TaskQueue extends ConcurrentLinkedQueue<ITask> {
    /**
      * 删除堆栈
      * */
     public void remove(ITask task){
         this.remove(task);
     }
}