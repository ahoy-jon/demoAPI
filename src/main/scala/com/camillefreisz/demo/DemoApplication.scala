package com.camillefreisz.demo

import fetch.FetchFeed
import vaadin.scala._

class DemoApplication extends Application("Démonstrateur API by Camille Freisz") {
  override val main: ComponentContainer = new VerticalLayout {

    /**
     * Gestion de la recherche
     */
    def search() {
      val list = FetchFeed.fetchUrl("http://api.vidal.fr/rest/api/products?q=" + searchField.value.get.toString)

      resultTable.p.removeAllItems()

      import collection.JavaConversions._ // Pour convertir une liste de Java à Scala
      var i = 0
      for (l <- list) {
        resultTable.p.addItem(Array(l.getTitle), i)
        i = i +1
      }
    }

    /**
     * Formulaire de recherche
     */
    val searchField = new TextField {
      prompt = "Search"
    }

    /**
     * Button de recherche
     */
    val goSearch = new Button {
      caption = "Go !"
      clickListeners += search()
    }

    /**
     * Table pour l'affichage des resultats
     */
    val resultTable = new Table {
      width = (100 percent)
      pageLength = 20
      selectionMode = SelectionMode.Single
      columnCollapsingAllowed = true
      columnReorderingAllowed = true
      this.p.addContainerProperty("Product", classOf[String], null)
    }

    /**
     * Disposition des éléments
     */
    margin = true
    components += Label("This Vaadin app uses ChewyMojo !")

    components += new HorizontalLayout {
      components += searchField
      components += goSearch
    }

    components += resultTable

  }
}