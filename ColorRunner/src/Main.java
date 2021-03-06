import java.util.LinkedList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Main implements ApplicationListener{
	
	
	SpriteBatch batch;
	//LinkedList<Entity> entities, toAdd, toRemove;
	Player player;
	Rectangle viewport;
	Level[] levels;
	int gameProg;
	int levelStatus;
	
	
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = 900;
		cfg.height = 700;
		cfg.fullscreen = false;
		cfg.useGL20 = true;
		cfg.title = "Coole Title";
		
		new LwjglApplication(new Main(), cfg);
		
	}
	@Override
	public void create() {	
		batch = new SpriteBatch();
		
		if(viewport == null){
			viewport = new Rectangle(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		}
		//entities = new LinkedList<Entity>();
		//toAdd = new LinkedList<Entity>();
		//toRemove = new LinkedList<Entity>();
		
		
		player = new Player("res/CharacterImage.png", 100, 100, 50, 50);
		//player.sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		//entities.add(player);
		
		
		LinkedList<Level> tempLvl = new LinkedList<Level>();
		levelStatus = 0;
		gameProg = 0;
		
		tempLvl.add(lev1());
		
		levels = (Level[]) tempLvl.toArray(new Level[0]);
		levels[gameProg].levelStart(Gdx.graphics.getDeltaTime());
		
		Gdx.input.setInputProcessor(player);		
	}

	@Override
	public void dispose() {	}

	@Override
	public void pause() {	}

	@Override
	public void render() {
		levels[gameProg].update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if(gameProg < 6)levels[gameProg].render(batch);
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {	}

	@Override
	public void resume() {	}
	
	public Level lev1(){
		Level lev = new Level(player, viewport);
		lev.makeWall("res/bullet.png", 10, 200, 800, 50);
		
		lev.makeWall("res/bullet.png", 100, 300, 80, 10);
		
		lev.makeWall("res/bullet.png", 200, 400, 100, 50);
		
		lev.makeWall("res/bullet.png", 800, 200, 20, 600);
		
		
		lev.setStartPos(100, 500);
		return lev;
	}
	
	
}
