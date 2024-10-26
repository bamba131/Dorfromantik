### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none -cp classes -d classes

JVM = java
JVMFLAGS = -cp classes

### RÈGLES ESSENTIELLES ###

Main.class: Main.java java/controller/MenuController.class java/view/MenuView.class java/controller/GameController.class
	$(JC) $(JCFLAGS) Main.java

# Compilation des contrôleurs
java/controller/MenuController.class: java/controller/MenuController.java java/model/MenuModel.class java/view/MenuView.class java/controller/NewListener.class java/controller/QuiListener.class java/controller/ResListener.class
	$(JC) $(JCFLAGS) java/controller/MenuController.java

java/controller/NewListener.class: java/controller/NewListener.java
	$(JC) $(JCFLAGS) java/controller/NewListener.java

java/controller/QuiListener.class: java/controller/QuiListener.java
	$(JC) $(JCFLAGS) java/controller/QuiListener.java

java/controller/ResListener.class: java/controller/ResListener.java java/view/GameView.class java/view/App.class 
	$(JC) $(JCFLAGS) java/controller/ResListener.java

java/controller/GameController.class: java/controller/GameController.java java/controller/TilePlacer.class java/model/Tile.class java/controller/CameraController.class java/controller/HexagonMouseListener.class
	$(JC) $(JCFLAGS) java/controller/GameController.java

java/controller/MouseWheelController.class: java/controller/MouseWheelController.java java/view/HexagonTile.class java/controller/GameController.class
	$(JC) $(JCFLAGS) java/controller/MouseWheelController.java

java/controller/HexagonMouseListener.class: java/controller/HexagonMouseListener.java java/view/HexagonTile.class java/controller/TilePlacer.class
	$(JC) $(JCFLAGS) java/controller/HexagonMouseListener.java

java/controller/CameraController.class: java/controller/CameraController.java java/controller/GameContext.class java/controller/MousePressHandler.class java/controller/MouseDragHandler.class
	$(JC) $(JCFLAGS) java/controller/CameraController.java

java/controller/GameContext.class: java/controller/GameContext.java java/view/HexagonTile.class
	$(JC) $(JCFLAGS) java/controller/GameContext.java

# Compilation des vues
java/view/GameView.class: java/view/GameView.java java/controller/MouseWheelController.class java/controller/GameController.class java/view/HexagonTile.class
	$(JC) $(JCFLAGS) java/view/GameView.java

java/view/MenuView.class: java/view/MenuView.java java/view/BtnPerso.class java/view/ButtonHoverListener.class
	$(JC) $(JCFLAGS) java/view/MenuView.java

java/view/HexagonTile.class: java/view/HexagonTile.java java/model/Tile.class java/model/TerrainType.class
	$(JC) $(JCFLAGS) java/view/HexagonTile.java

# Compilation des modèles
java/model/MenuModel.class: java/model/MenuModel.java
	$(JC) $(JCFLAGS) java/model/MenuModel.java

java/model/Tile.class: java/model/Tile.java java/model/TerrainType.class
	$(JC) $(JCFLAGS) java/model/Tile.java

java/model/TerrainType.class: java/model/TerrainType.java
	$(JC) $(JCFLAGS) java/model/TerrainType.java

# Compilation des composants individuels
java/view/BtnPerso.class: java/view/BtnPerso.java
	$(JC) $(JCFLAGS) java/view/BtnPerso.java

java/view/ButtonHoverListener.class: java/view/ButtonHoverListener.java
	$(JC) $(JCFLAGS) java/view/ButtonHoverListener.java

java/view/App.class: java/view/App.java
	$(JC) $(JCFLAGS) java/view/App.java

# Compilation des handlers
java/controller/MousePressHandler.class: java/controller/MousePressHandler.java java/controller/CameraControllerListener.class
	$(JC) $(JCFLAGS) java/controller/MousePressHandler.java

java/controller/MouseDragHandler.class: java/controller/MouseDragHandler.java java/controller/CameraControllerListener.class
	$(JC) $(JCFLAGS) java/controller/MouseDragHandler.java

java/controller/CameraControllerListener.class: java/controller/CameraControllerListener.java
	$(JC) $(JCFLAGS) java/controller/CameraControllerListener.java

java/controller/TilePlacer.class: java/controller/TilePlacer.java
	$(JC) $(JCFLAGS) java/controller/TilePlacer.java

### RÈGLE POUR COPIER LES IMAGES ###

copy-images:
	mkdir -p classes/java/view/img
	if [ -d java/view/img ]; then cp -r java/view/img/* classes/java/view/img/; else echo "Le répertoire java/view/img n'existe pas."; fi

### RÈGLES OPTIONNELLES ###

run: Main.class copy-images
	$(JVM) $(JVMFLAGS) main.Main

clean:
	-rm -f classes/**/*.class

mrproper: clean
	-rm -rf classes/java/view/img

### BUTS FACTICES ###

.PHONY: run clean mrproper copy-images

### FIN ###
