package com.muxin.demo.designpattern;

/**
 * @Projectname: muxin
 * @Filename: ChainOfResponsibilityPattern
 * @Author: yangxz
 * @Data:2022/12/29 16:23
 * @Description: 调用链模式
 */
public class ChainOfResponsibilityPattern {


    public static void main(String[] args) {

        DirectorApprovalHandler directorApprovalHandler = new DirectorApprovalHandler();
        HrApprovalHandler hrApprovalHandler = new HrApprovalHandler();
        GroupLeaderApprovalHandler groupLeaderApprovalHandler = new GroupLeaderApprovalHandler();
        groupLeaderApprovalHandler.nextHandler(directorApprovalHandler);
        directorApprovalHandler.nextHandler(hrApprovalHandler);


        groupLeaderApprovalHandler.approval(new ApprovalContext());
    }





    //组长审批实现
    public static class GroupLeaderApprovalHandler extends ApprovalHandler {
        @Override
        public void approval(ApprovalContext approvalContext) {
            System.out.println("组长审批");
            //调用下一个处理对象进行处理
            invokeNext(approvalContext);
        }
    }

    //主管审批实现
    public static class DirectorApprovalHandler extends ApprovalHandler {
        @Override
        public void approval(ApprovalContext approvalContext) {
            System.out.println("主管审批");
            //调用下一个处理对象进行处理
            invokeNext(approvalContext);
        }
    }

    //hr审批实现
    public static class HrApprovalHandler extends ApprovalHandler {
        @Override
        public void approval(ApprovalContext approvalContext) {
            System.out.println("hr审批");
            //调用下一个处理对象进行处理
            invokeNext(approvalContext);
        }
    }

    public static abstract class ApprovalHandler {

        /**
         * 责任链中的下一个处理对象
         */
        protected ApprovalHandler next;

        /**
         * 设置下一个处理对象
         *
         * @param approvalHandler
         */
        public void nextHandler(ApprovalHandler approvalHandler) {
            this.next = approvalHandler;
        }

        /**
         * 处理
         *
         * @param approvalContext
         */
        public abstract void approval(ApprovalContext approvalContext);

        /**
         * 调用下一个处理对象
         *
         * @param approvalContext
         */
        protected void invokeNext(ApprovalContext approvalContext) {
            if (next != null) {
                next.approval(approvalContext);
            }
        }

    }

    public static class ApprovalContext{}
}
