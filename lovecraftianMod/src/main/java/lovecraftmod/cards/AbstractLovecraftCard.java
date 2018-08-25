/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lovecraftmod.cards;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.CurScreen;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;

/**
 *
 * @author rey
 */
public abstract class AbstractLovecraftCard extends CustomCard {
    
    protected boolean hasCycled = false;
    protected boolean megaUpgraded = false;
    public boolean forcedUpgrade = false;
    
    public boolean rebound = false;
    
    public AbstractLovecraftCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, int cardPool) {
    super(id, name, img, cost, rawDescription, type, color, rarity, target);
}
    
}
