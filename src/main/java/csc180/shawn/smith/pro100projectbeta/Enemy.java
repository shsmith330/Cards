//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package csc180.shawn.smith.pro100projectbeta;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class Enemy {
    private int hp;
    private String name;
    private boolean dead;
    private String image;
    private boolean isAggressive;
    private boolean turn = false;
    private int sheildCount = 0;

    public int getSheildCount() {
        return this.sheildCount;
    }

    public void setSheildCount(int sheildCount) {
        this.sheildCount = sheildCount;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public void setAggressive(boolean aggressive) {
        isAggressive = aggressive;
    }

    public boolean isAggressive() {
        return this.isAggressive;
    }

    public boolean isTurn() {
        return this.turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Enemy(int hp, String name, String image, boolean dead) {
        this.hp = hp;
        this.name = name;
        this.image = image;
        this.dead = dead;
        this.isAggressive = false;
    }

    public Enemy(int hp, String name, boolean dead) {
        this.hp = hp;
        this.name = name;
        this.dead = dead;
        this.isAggressive = false;
    }

    public boolean isDead() {
        if (this.hp <= 0) {
            this.dead = true;
        }

        return this.dead;
    }

    public String toString() {
        return "Enemy{hp=" + this.hp + ", name='" + this.name + "', dead=" + this.dead + ", image='" + this.image + "', isAggressive=" + this.isAggressive + "}";
    }

    public String toStringTemp() {
        return "Enemy{hp=" + this.hp + ", name='" + this.name + "', dead=" + this.dead + ", isAggressive=" + this.isAggressive + "}";
    }

    public abstract static class Card {
        private String name;
        private String value;
        private String image;
        private boolean isattack;
        private String id;

        public boolean isIsattack() {
            return this.isattack;
        }

        public void setIsattack(boolean isattack) {
            this.isattack = isattack;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return this.value;
        }

        public void setDescription(String value) {
            this.value = value;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return this.image;
        }

        public String setImage(String Url) throws IOException {
            this.image = Url;
            return image;
        }

        public Card(JSONObject jsObject) throws IOException {
            this.setName((String)jsObject.get("name"));
            long num = (Long)jsObject.get("id");
            this.setId(String.valueOf(num));
            this.setIsattack((Boolean)jsObject.get("isAttack"));
            long v=(Long)jsObject.get("value");
            this.setDescription(String.valueOf(v));
            this.image=setImage((String) jsObject.get("url"));
        }

        @Override
        public String toString() {
            return "Card{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", image='" + image + '\'' +
                    ", isattack=" + isattack +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    public static class attackCards extends Card {
        public attackCards(JSONObject jsObject) throws IOException, ParseException {
            super(jsObject);
        }

        public String toStringtemp() {
            return "New Attack Card!";
        }
    }
}
