package com.muxin.system.designpattern;

/**
 * @Projectname: muxin
 * @Filename: FactoryMethodPattern
 * @Author: yangxz
 * @Data:2022/12/29 15:27
 * @Description: TODO
 */
public class FactoryMethodPattern {

    public static void main(String[] args) {

        AnimalFactory animalFactory = new CatFactory();
        Animal cat = animalFactory.createAnimal();
        cat.run();

    }

    /** * 工厂接口 */
    public interface AnimalFactory {
        Animal createAnimal();
    }

    /** * 小猫实现 */
    public static class CatFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            Cat cat = new Cat();
            //一系列复杂操作
            return cat;
        }
    }

    /** * 小狗实现 */
    public static class DogFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            Dog dog = new Dog();
            //一系列复杂操作
            return dog;
        }
    }


    public static abstract class Animal{
        abstract void run();
    }

    public static class Dog extends Animal{
        @Override
        void run() {
            System.out.println("dog run");
        }
    }
    public static class Cat extends Animal{
        @Override
        void run() {
            System.out.println("cat run");
        }
    }

}
