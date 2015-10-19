package com.envoy.rater.orm;

import com.envoy.rater.orm.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "GAME".
 */
public class Game {

    private Long id;
    private String gameName;
    private String consoleName;
    private byte[] gameImage;
    private Boolean isGameFinished;
    private Integer starsNumber;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient GameDao myDao;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Game(Long id, String gameName, String consoleName, byte[] gameImage, Boolean isGameFinished, Integer starsNumber) {
        this.id = id;
        this.gameName = gameName;
        this.consoleName = consoleName;
        this.gameImage = gameImage;
        this.isGameFinished = isGameFinished;
        this.starsNumber = starsNumber;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGameDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public byte[] getGameImage() {
        return gameImage;
    }

    public void setGameImage(byte[] gameImage) {
        this.gameImage = gameImage;
    }

    public Boolean getIsGameFinished() {
        return isGameFinished;
    }

    public void setIsGameFinished(Boolean isGameFinished) {
        this.isGameFinished = isGameFinished;
    }

    public Integer getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(Integer starsNumber) {
        this.starsNumber = starsNumber;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
