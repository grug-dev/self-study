package com.kkpa.springtutorial.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Elephant extends Animal {

}
