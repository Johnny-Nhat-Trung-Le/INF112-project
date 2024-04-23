package inf112.skeleton.app.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemMushroom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItem{
    privatestaticfinalintVELOCITY_ITERATIONS=6;
    privatestaticfinalintPOSITION_ITERATIONS=2;
    privatestaticfinalintNUM_ITERATIONS=60;
    privatestaticfinalfloatDT=1/60f;
    privatestaticfinalfloatINIT_X=0;
    privatestaticfinalfloatINIT_Y=30;
    privatestaticfinalfloatGRAVITY_X=0;
    privatestaticfinalfloatGRAVITY_Y=0;
    privateWorldworld;
    privatePlayerModelplayer;
    privateEventBusbus;

    privatevoidstep(){
        player.step(DT);
        world.step(DT,VELOCITY_ITERATIONS,POSITION_ITERATIONS);
    }

    @BeforeEach
            publicvoidreset(){
        world=newWorld(newVector2(GRAVITY_X,GRAVITY_Y),true);
        bus=newEventBus();
        player=newPlayerModel(bus,world,INIT_X,INIT_Y);
        world.setContactListener(player);
    }

    @Test
            publicvoidpickUpEnergy(){
        player.moveRight(true);
        assertNull(player.getItem(),"playershouldnotstartwithanitem");
        ItemEnergyenergy=newItemEnergy(bus,world,player.getX(),player.getY());
        world.setContactListener(energy);
        step();
        assertNotNull(player.getItem(),"Supposetohaveanitemintheplayerinventory");

    }

    @Test
            publicvoidpickUpMushroom(){
        player.moveLeft(true);
        assertNull(player.getItem(),"playershouldnotstartwithanitem");
        ItemMushroommushroom=newItemMushroom(bus,world,player.getX(),player.getY());
        world.setContactListener(mushroom);
        step();
        assertNotNull(player.getItem(),"Supposetohaveanitemintheplayerinventory");
    }

    @Test
            publicvoidpickUpOnlyOneItem(){
        player.moveRight(true);
//TODOFIXHasmushroominsteadofenergy
        ItemMushroommushroom=newItemMushroom(bus,world,INIT_X+player.getWidth(),player.getY());
        ItemEnergyenergy=newItemEnergy(bus,world,INIT_X+(2*player.getWidth()),player.getY());
        world.setContactListener(mushroom);
        world.setContactListener(energy);
        System.out.println(mushroom.getX()+"mushroomX");
        System.out.println(energy.getX()+"energyX");
        for(inti=0;i<NUM_ITERATIONS;i++){
            step();
        }
        System.out.println(player.getX());
        System.out.println(player.getItem());
        assertNotNull(player.getItem(),"Supposetohaveanitemintheplayerinventory");
        assertEquals(mushroom.toString(),player.getItem().toString(),"Shouldbethefirstitemwhichismushroom");

    }
    @Test
            publicvoidpickUpItemAfterUsed(){
        player.moveLeft(true);
        assertNull(player.getItem(),"playershouldnotstartwithanitem");
        ItemMushroommushroom=newItemMushroom(bus,world,player.getX(),player.getY());
        world.setContactListener(mushroom);
        step();
        assertEquals(mushroom.toString(),player.getItem().toString(),"Theitemshouldbeamushroom");
        for(inti=0;i<mushroom.getDurability().maximum();i++){
            player.useItem();
            step();
        }
        ItemEnergyenergy=newItemEnergy(bus,world,player.getX(),player.getY());
        world.setContactListener(energy);
        step();
        assertEquals(energy.toString(),player.getItem().toString(),"Theitemshouldnowbeenergy");

    }
    @Test
            publicvoidtestUseEnergyDistance(){
        player.moveRight(true);

        for(inti=0;i<NUM_ITERATIONS;i++){
            step();
        }
        floatdiffX=player.getX()-INIT_X;
//AddenergyItemtotheworldandlettheplayeruseit
        ItemEnergyenergy=newItemEnergy(bus,world,player.getX(),player.getY());
        world.setContactListener(energy);
        step();
        floatstartXWithItem=player.getX();
        player.useItem();
        for(inti=0;i<NUM_ITERATIONS;i++){
            step();
        }
        floatdiffXWithItem=player.getX()-startXWithItem;
        assertTrue(diffXWithItem>diffX,"Thedistanceusedwiththeenergyitemshouldbelargerthandistancewithoutusingenergyitem");
    }


    @Test
            publicvoidtestUseMushroomJump(){
        floatmaxHeight=jumpWithoutItem();
        reset();
        world.setGravity(newVector2(GRAVITY_X,-20));
        ItemMushroommushroom=newItemMushroom(bus,world,player.getX(),player.getY());
        world.setContactListener(mushroom);
        step();
        createStaticBody();
        assertNotNull(player.getItem(),"PlayershouldhavegottenItemMushroom");
        floatpreviousY=player.getY()+player.getHeight();
        player.moveUp(true);
        player.useItem();
        floatmaxHeightItem=0;
        while(true){
            step();
            floatcurrentY=player.getY();
            if(currentY<previousY){
                maxHeightItem=previousY;
                break;
            }
            previousY=currentY;
        }
        System.out.println(maxHeightItem);
        System.out.println(maxHeight);
        assertTrue(maxHeightItem>maxHeight,"usingmushroomshouldmakeyoujumphigher");


    }

    privatefloatjumpWithoutItem(){
        createStaticBody();
        world.setGravity(newVector2(GRAVITY_X,-20));
        floatinitialY=player.getY()+player.getHeight();
        floatpreviousY=initialY;
        floatmaxHeight=0;
        player.moveUp(true);
        while(true){
            step();
            floatcurrentY=player.getY();
            if(currentY<previousY){

                maxHeight=previousY;
                break;
            }
            previousY=currentY;
        }
        returnmaxHeight;
    }
    privatevoidcreateStaticBody(){
        floatwidth=10;
        floatheight=2;
        BodyDefground=newBodyDef();
        ground.type=BodyDef.BodyType.StaticBody;
        floatgroundY=INIT_Y-10;
        ground.position.set(0,groundY);
        BodygroundBody=world.createBody(ground);
        PolygonShapegroundShape=newPolygonShape();
        groundShape.setAsBox(width/2,height/2);
        FixtureDeffixtureDef=newFixtureDef();
        fixtureDef.density=1;
        fixtureDef.friction=0.5f;
        fixtureDef.restitution=0;
        fixtureDef.shape=groundShape;
        groundBody.createFixture(fixtureDef);


    }


    @Test
            publicvoidtestNoDuplicateUseEnergy(){
        player.moveRight(true);
        ItemEnergyenergy=newItemEnergy(bus,world,player.getX(),player.getY());
        world.setContactListener(energy);
        step();
        floatstartX=player.getX();
        player.useItem();
        for(inti=0;i<NUM_ITERATIONS;i++){
            step();
        }
        floatdiffX=player.getX()-startX;
//Usingenergyitemtwiceormoreshouldnotgivemorespeed.
        player.useItem();
        player.useItem();
        startX=player.getX();
        for(inti=0;i<NUM_ITERATIONS;i++){
            step();
        }
//Approximatingthedifferencesinceweareworkingwithfloatingpoints
        floattolerance=1f;
        floatdoubleEnergyDiff=player.getX()-startX;
        assertTrue(diffX-tolerance<doubleEnergyDiff&&doubleEnergyDiff<diffX+tolerance);
    }
    @Test
            publicvoidtestNoDuplicateUseMushroom(){
//TODO
    }
