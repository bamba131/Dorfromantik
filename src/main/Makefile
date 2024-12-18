### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none -cp classes -d classes

JVM = java
JVMFLAGS = -cp "classes:mariadb.jar"
MANIFEST_FILE = META-INF/MANIFEST.MF

### RÈGLES ESSENTIELLES ###

Main.class: Main.java  java/view/MenuView.class java/controller/GameController.class java/view/App.class
	$(JC) $(JCFLAGS) Main.java

java/controller/NewListener.class: java/controller/NewListener.java
	$(JC) $(JCFLAGS) java/controller/NewListener.java

java/controller/QuiListener.class: java/controller/QuiListener.java
	$(JC) $(JCFLAGS) java/controller/QuiListener.java

java/controller/ResListener.class: java/controller/ResListener.java java/view/GameView.class java/view/App.class
	$(JC) $(JCFLAGS) java/controller/ResListener.java

java/controller/SeriesSelector.class: java/controller/SeriesSelector.java java/view/GameView.class
	$(JC) $(JCFLAGS) java/controller/SeriesSelector.java

java/controller/GameController.class: java/controller/GameController.java java/controller/ScoreGameContext.class java/controller/TilePlacer.class java/model/Tile.class java/controller/CameraController.class java/controller/HexagonMouseListener.class java/model/TileDatabaseManager.class java/controller/SendScore.class java/controller/GameEndListener.class
	$(JC) $(JCFLAGS) java/controller/GameController.java

java/controller/MouseWheelController.class: java/controller/MouseWheelController.java java/view/HexagonTile.class java/controller/GameController.class
	$(JC) $(JCFLAGS) java/controller/MouseWheelController.java

java/controller/HexagonMouseListener.class: java/controller/HexagonMouseListener.java java/view/HexagonTile.class java/controller/TilePlacer.class
	$(JC) $(JCFLAGS) java/controller/HexagonMouseListener.java

java/controller/CameraController.class: java/controller/CameraController.java java/controller/GameContext.class java/controller/MousePressHandler.class java/controller/MouseDragHandler.class
	$(JC) $(JCFLAGS) java/controller/CameraController.java

java/controller/GameContext.class: java/controller/GameContext.java java/view/HexagonTile.class
	$(JC) $(JCFLAGS) java/controller/GameContext.java

java/view/GameView.class: java/view/GameView.java java/view/App.class java/view/ScoreView.class java/controller/MouseWheelController.class java/controller/GameController.class java/view/HexagonTile.class
	$(JC) $(JCFLAGS) java/view/GameView.java

java/view/MenuView.class: java/view/MenuView.java java/view/BtnPerso.class java/view/ButtonHoverListener.class java/controller/SeriesSelector.class
	$(JC) $(JCFLAGS) java/view/MenuView.java

java/view/HexagonTile.class: java/view/HexagonTile.java java/model/Tile.class java/model/TerrainType.class
	$(JC) $(JCFLAGS) java/view/HexagonTile.java

java/model/MenuModel.class: java/model/MenuModel.java
	$(JC) $(JCFLAGS) java/model/MenuModel.java

java/model/Tile.class: java/model/Tile.java java/model/TerrainType.class
	$(JC) $(JCFLAGS) java/model/Tile.java

java/model/TerrainType.class: java/model/TerrainType.java
	$(JC) $(JCFLAGS) java/model/TerrainType.java

java/view/BtnPerso.class: java/view/BtnPerso.java
	$(JC) $(JCFLAGS) java/view/BtnPerso.java

java/view/ButtonHoverListener.class: java/view/ButtonHoverListener.java
	$(JC) $(JCFLAGS) java/view/ButtonHoverListener.java

java/controller/MousePressHandler.class: java/controller/MousePressHandler.java java/controller/CameraControllerListener.class
	$(JC) $(JCFLAGS) java/controller/MousePressHandler.java

java/controller/MouseDragHandler.class: java/controller/MouseDragHandler.java java/controller/CameraControllerListener.class
	$(JC) $(JCFLAGS) java/controller/MouseDragHandler.java

java/controller/CameraControllerListener.class: java/controller/CameraControllerListener.java
	$(JC) $(JCFLAGS) java/controller/CameraControllerListener.java

java/controller/TilePlacer.class: java/controller/TilePlacer.java
	$(JC) $(JCFLAGS) java/controller/TilePlacer.java

java/model/TileDatabaseManager.class: java/model/TileDatabaseManager.java java/model/Tile.class
	$(JC) $(JCFLAGS) java/model/TileDatabaseManager.java

java/controller/ScoreGameContext.class: java/controller/ScoreGameContext.java java/controller/GameContext.class java/model/Pocket.class java/model/TerrainType.class java/view/HexagonTile.class
	$(JC) $(JCFLAGS) java/controller/ScoreGameContext.java

java/controller/SerieListener.class: java/controller/SerieListener.java java/controller/GameController.class
	$(JC) $(JCFLAGS) java/controller/SerieListener.java

java/model/Pocket.class: java/model/Pocket.java java/model/TerrainType.class
	$(JC) $(JCFLAGS) java/model/Pocket.java

java/controller/SendScore.class: java/controller/SendScore.java
	$(JC) $(JCFLAGS) java/controller/SendScore.java

java/controller/GameEndListener.class: java/controller/GameEndListener.java
	$(JC) $(JCFLAGS) java/controller/GameEndListener.java

java/view/App.class: java/view/App.java
	$(JC) $(JCFLAGS) java/view/App.java

java/view/ScoreView.class: java/view/ScoreView.java java/view/App.class java/controller/AllScore.class java/view/BtnPerso.class java/controller/BackButtonMouseListener.class
	$(JC) $(JCFLAGS) java/view/ScoreView.java

java/controller/AllScore.class: java/controller/AllScore.java
	$(JC) $(JCFLAGS) java/controller/AllScore.java

java/controller/BackButtonMouseListener.class: java/controller/BackButtonMouseListener.java
	$(JC) $(JCFLAGS) java/controller/BackButtonMouseListener.java

### RÈGLE POUR COPIER LES IMAGES ###

Music:
	mkdir -p classes/java/Music
	if [ -d java/Music ]; then cp -r java/Music/* classes/java/Music/; else echo "Le répertoire java/Music n'existe pas."; fi

copy-images:
	mkdir -p classes/java/view/img
	if [ -d java/view/img ]; then cp -r java/view/img/* classes/java/view/img/; else echo "Le répertoire java/view/img n'existe pas."; fi

$(MANIFEST_FILE):
	mkdir -p META-INF
	echo "Manifest-Version: 1.0" > $(MANIFEST_FILE)
	echo "Main-Class: main.Main" >> $(MANIFEST_FILE)
	echo "Class-Path: mariadb.jar" >> $(MANIFEST_FILE)

### ARCHIVE JAR ###

Main.jar: Main.class copy-images Music $(MANIFEST_FILE)
	mkdir -p classes/java/Music/
	mkdir -p classes/java/view/img  
	jar cfm Main.jar $(MANIFEST_FILE) -C classes .

### RÈGLES OPTIONNELLES ###

run: Main.class copy-images
	$(JVM) $(JVMFLAGS) main.Main

clean:
	-rm -f classes/**/*.class
	-rm -rf classes/java/view/img
	-rm -rf classes/Music

### BUTS FACTICES ###

.PHONY: run clean copy-images

### FIN ###