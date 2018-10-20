package com.todolist.amin.todo;

public class Task {
        
        private String desc;
        private boolean isSubTask = false;

        public Task(String desc, boolean isSubTask) {
            this.desc = desc;
            this.isSubTask = isSubTask;
        }

        public String getDesc() {
            return desc;
        }
        
        public boolean getIsSubtask(){
            return isSubTask;
        }

        @Override
        public String toString() {
            return desc;
        }
}