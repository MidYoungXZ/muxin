package com.muxin.demo.designpattern;

/**
 * @Projectname: muxin
 * @Filename: DecoratorPattern
 * @Author: yangxz
 * @Data:2023/2/9 15:24
 * @Description:
 */
public class DecoratorPattern {



    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("Circle of red border");
        redCircle.draw();

        System.out.println("Rectangle of red border");
        redRectangle.draw();
    }


    public interface Shape {
        void draw();
    }

    static class Rectangle implements Shape {

        @Override
        public void draw() {
            System.out.println("Shape: Rectangle");
        }
    }
    static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Shape: Circle");
        }
    }

    static abstract class ShapeDecorator implements Shape {
        protected Shape decoratedShape;

        public ShapeDecorator(Shape decoratedShape){
            this.decoratedShape = decoratedShape;
        }

        public void draw(){
            decoratedShape.draw();
        }
    }

    static class RedShapeDecorator extends ShapeDecorator {

        public RedShapeDecorator(Shape decoratedShape) {
            super(decoratedShape);
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }

        private void setRedBorder(Shape decoratedShape){
            System.out.println("Border Color: Red");
        }
    }

}
