package com.camillefreisz.demo

import vaadin.scala._

class DemoApplication extends Application("D��monstrateur API by Camille Freisz") {
	override val main: ComponentContainer = new VerticalLayout {
		margin = true
		components += Label("This Vaadin app uses Scaladin!")
	}
}