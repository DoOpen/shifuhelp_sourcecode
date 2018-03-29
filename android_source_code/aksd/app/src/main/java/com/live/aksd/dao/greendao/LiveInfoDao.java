package com.live.aksd.dao.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.live.aksd.bean.LiveInfo;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LIVE_INFO".
*/
public class LiveInfoDao extends AbstractDao<LiveInfo, Void> {

    public static final String TABLENAME = "LIVE_INFO";

    /**
     * Properties of entity LiveInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property No = new Property(0, String.class, "no", false, "NO");
        public final static Property Nick = new Property(1, String.class, "nick", false, "NICK");
        public final static Property Avatar = new Property(2, String.class, "avatar", false, "AVATAR");
        public final static Property Follow = new Property(3, int.class, "follow", false, "FOLLOW");
        public final static Property Id = new Property(4, String.class, "id", false, "ID");
        public final static Property Uid = new Property(5, String.class, "uid", false, "UID");
        public final static Property Title = new Property(6, String.class, "title", false, "TITLE");
        public final static Property Category_id = new Property(7, String.class, "category_id", false, "CATEGORY_ID");
        public final static Property Slug = new Property(8, String.class, "slug", false, "SLUG");
        public final static Property Intro = new Property(9, String.class, "intro", false, "INTRO");
        public final static Property Announcement = new Property(10, String.class, "announcement", false, "ANNOUNCEMENT");
        public final static Property Cover = new Property(11, String.class, "cover", false, "COVER");
        public final static Property Play_at = new Property(12, String.class, "play_at", false, "PLAY_AT");
        public final static Property Last_play_at = new Property(13, String.class, "last_play_at", false, "LAST_PLAY_AT");
        public final static Property View = new Property(14, String.class, "view", false, "VIEW");
        public final static Property Status = new Property(15, String.class, "status", false, "STATUS");
        public final static Property Priv = new Property(16, String.class, "priv", false, "PRIV");
        public final static Property Landscape = new Property(17, String.class, "landscape", false, "LANDSCAPE");
        public final static Property Position = new Property(18, String.class, "position", false, "POSITION");
        public final static Property Weight = new Property(19, String.class, "weight", false, "WEIGHT");
        public final static Property Check = new Property(20, String.class, "check", false, "CHECK");
        public final static Property Recommend_image = new Property(21, String.class, "recommend_image", false, "RECOMMEND_IMAGE");
        public final static Property VideoQuality = new Property(22, String.class, "videoQuality", false, "VIDEO_QUALITY");
        public final static Property Category_name = new Property(23, String.class, "category_name", false, "CATEGORY_NAME");
        public final static Property Screen = new Property(24, int.class, "screen", false, "SCREEN");
        public final static Property Start_time = new Property(25, String.class, "start_time", false, "START_TIME");
        public final static Property Stream = new Property(26, String.class, "stream", false, "STREAM");
        public final static Property Thumb = new Property(27, String.class, "thumb", false, "THUMB");
        public final static Property Video = new Property(28, String.class, "video", false, "VIDEO");
        public final static Property App_shuffling_image = new Property(29, String.class, "app_shuffling_image", false, "APP_SHUFFLING_IMAGE");
        public final static Property CategoryId = new Property(30, String.class, "categoryId", false, "CATEGORY_ID");
        public final static Property Hidden = new Property(31, boolean.class, "hidden", false, "HIDDEN");
        public final static Property Play_status = new Property(32, boolean.class, "play_status", false, "PLAY_STATUS");
        public final static Property Icontext = new Property(33, String.class, "icontext", false, "ICONTEXT");
        public final static Property Category_slug = new Property(34, String.class, "category_slug", false, "CATEGORY_SLUG");
    }


    public LiveInfoDao(DaoConfig config) {
        super(config);
    }
    
    public LiveInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LIVE_INFO\" (" + //
                "\"NO\" TEXT," + // 0: no
                "\"NICK\" TEXT," + // 1: nick
                "\"AVATAR\" TEXT," + // 2: avatar
                "\"FOLLOW\" INTEGER NOT NULL ," + // 3: follow
                "\"ID\" TEXT," + // 4: id
                "\"UID\" TEXT," + // 5: uid
                "\"TITLE\" TEXT," + // 6: title
                "\"CATEGORY_ID\" TEXT," + // 7: category_id
                "\"SLUG\" TEXT," + // 8: slug
                "\"INTRO\" TEXT," + // 9: intro
                "\"ANNOUNCEMENT\" TEXT," + // 10: announcement
                "\"COVER\" TEXT," + // 11: cover
                "\"PLAY_AT\" TEXT," + // 12: play_at
                "\"LAST_PLAY_AT\" TEXT," + // 13: last_play_at
                "\"VIEW\" TEXT," + // 14: view
                "\"STATUS\" TEXT," + // 15: status
                "\"PRIV\" TEXT," + // 16: priv
                "\"LANDSCAPE\" TEXT," + // 17: landscape
                "\"POSITION\" TEXT," + // 18: position
                "\"WEIGHT\" TEXT," + // 19: weight
                "\"CHECK\" TEXT," + // 20: check
                "\"RECOMMEND_IMAGE\" TEXT," + // 21: recommend_image
                "\"VIDEO_QUALITY\" TEXT," + // 22: videoQuality
                "\"CATEGORY_NAME\" TEXT," + // 23: category_name
                "\"SCREEN\" INTEGER NOT NULL ," + // 24: screen
                "\"START_TIME\" TEXT," + // 25: start_time
                "\"STREAM\" TEXT," + // 26: stream
                "\"THUMB\" TEXT," + // 27: thumb
                "\"VIDEO\" TEXT," + // 28: video
                "\"APP_SHUFFLING_IMAGE\" TEXT," + // 29: app_shuffling_image
                "\"CATEGORY_ID\" TEXT," + // 30: categoryId
                "\"HIDDEN\" INTEGER NOT NULL ," + // 31: hidden
                "\"PLAY_STATUS\" INTEGER NOT NULL ," + // 32: play_status
                "\"ICONTEXT\" TEXT," + // 33: icontext
                "\"CATEGORY_SLUG\" TEXT);"); // 34: category_slug
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LIVE_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LiveInfo entity) {
        stmt.clearBindings();
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(1, no);
        }
 
        String nick = entity.getNick();
        if (nick != null) {
            stmt.bindString(2, nick);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(3, avatar);
        }
        stmt.bindLong(4, entity.getFollow());
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(6, uid);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
 
        String category_id = entity.getCategory_id();
        if (category_id != null) {
            stmt.bindString(8, category_id);
        }
 
        String slug = entity.getSlug();
        if (slug != null) {
            stmt.bindString(9, slug);
        }
 
        String intro = entity.getIntro();
        if (intro != null) {
            stmt.bindString(10, intro);
        }
 
        String announcement = entity.getAnnouncement();
        if (announcement != null) {
            stmt.bindString(11, announcement);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(12, cover);
        }
 
        String play_at = entity.getPlay_at();
        if (play_at != null) {
            stmt.bindString(13, play_at);
        }
 
        String last_play_at = entity.getLast_play_at();
        if (last_play_at != null) {
            stmt.bindString(14, last_play_at);
        }
 
        String view = entity.getView();
        if (view != null) {
            stmt.bindString(15, view);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(16, status);
        }
 
        String priv = entity.getPriv();
        if (priv != null) {
            stmt.bindString(17, priv);
        }
 
        String landscape = entity.getLandscape();
        if (landscape != null) {
            stmt.bindString(18, landscape);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(19, position);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(20, weight);
        }
 
        String check = entity.getCheck();
        if (check != null) {
            stmt.bindString(21, check);
        }
 
        String recommend_image = entity.getRecommend_image();
        if (recommend_image != null) {
            stmt.bindString(22, recommend_image);
        }
 
        String videoQuality = entity.getVideoQuality();
        if (videoQuality != null) {
            stmt.bindString(23, videoQuality);
        }
 
        String category_name = entity.getCategory_name();
        if (category_name != null) {
            stmt.bindString(24, category_name);
        }
        stmt.bindLong(25, entity.getScreen());
 
        String start_time = entity.getStart_time();
        if (start_time != null) {
            stmt.bindString(26, start_time);
        }
 
        String stream = entity.getStream();
        if (stream != null) {
            stmt.bindString(27, stream);
        }
 
        String thumb = entity.getThumb();
        if (thumb != null) {
            stmt.bindString(28, thumb);
        }
 
        String video = entity.getVideo();
        if (video != null) {
            stmt.bindString(29, video);
        }
 
        String app_shuffling_image = entity.getApp_shuffling_image();
        if (app_shuffling_image != null) {
            stmt.bindString(30, app_shuffling_image);
        }
 
        String categoryId = entity.getCategoryId();
        if (categoryId != null) {
            stmt.bindString(31, categoryId);
        }
        stmt.bindLong(32, entity.getHidden() ? 1L: 0L);
        stmt.bindLong(33, entity.getPlay_status() ? 1L: 0L);
 
        String icontext = entity.getIcontext();
        if (icontext != null) {
            stmt.bindString(34, icontext);
        }
 
        String category_slug = entity.getCategory_slug();
        if (category_slug != null) {
            stmt.bindString(35, category_slug);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LiveInfo entity) {
        stmt.clearBindings();
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(1, no);
        }
 
        String nick = entity.getNick();
        if (nick != null) {
            stmt.bindString(2, nick);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(3, avatar);
        }
        stmt.bindLong(4, entity.getFollow());
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(6, uid);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(7, title);
        }
 
        String category_id = entity.getCategory_id();
        if (category_id != null) {
            stmt.bindString(8, category_id);
        }
 
        String slug = entity.getSlug();
        if (slug != null) {
            stmt.bindString(9, slug);
        }
 
        String intro = entity.getIntro();
        if (intro != null) {
            stmt.bindString(10, intro);
        }
 
        String announcement = entity.getAnnouncement();
        if (announcement != null) {
            stmt.bindString(11, announcement);
        }
 
        String cover = entity.getCover();
        if (cover != null) {
            stmt.bindString(12, cover);
        }
 
        String play_at = entity.getPlay_at();
        if (play_at != null) {
            stmt.bindString(13, play_at);
        }
 
        String last_play_at = entity.getLast_play_at();
        if (last_play_at != null) {
            stmt.bindString(14, last_play_at);
        }
 
        String view = entity.getView();
        if (view != null) {
            stmt.bindString(15, view);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(16, status);
        }
 
        String priv = entity.getPriv();
        if (priv != null) {
            stmt.bindString(17, priv);
        }
 
        String landscape = entity.getLandscape();
        if (landscape != null) {
            stmt.bindString(18, landscape);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(19, position);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(20, weight);
        }
 
        String check = entity.getCheck();
        if (check != null) {
            stmt.bindString(21, check);
        }
 
        String recommend_image = entity.getRecommend_image();
        if (recommend_image != null) {
            stmt.bindString(22, recommend_image);
        }
 
        String videoQuality = entity.getVideoQuality();
        if (videoQuality != null) {
            stmt.bindString(23, videoQuality);
        }
 
        String category_name = entity.getCategory_name();
        if (category_name != null) {
            stmt.bindString(24, category_name);
        }
        stmt.bindLong(25, entity.getScreen());
 
        String start_time = entity.getStart_time();
        if (start_time != null) {
            stmt.bindString(26, start_time);
        }
 
        String stream = entity.getStream();
        if (stream != null) {
            stmt.bindString(27, stream);
        }
 
        String thumb = entity.getThumb();
        if (thumb != null) {
            stmt.bindString(28, thumb);
        }
 
        String video = entity.getVideo();
        if (video != null) {
            stmt.bindString(29, video);
        }
 
        String app_shuffling_image = entity.getApp_shuffling_image();
        if (app_shuffling_image != null) {
            stmt.bindString(30, app_shuffling_image);
        }
 
        String categoryId = entity.getCategoryId();
        if (categoryId != null) {
            stmt.bindString(31, categoryId);
        }
        stmt.bindLong(32, entity.getHidden() ? 1L: 0L);
        stmt.bindLong(33, entity.getPlay_status() ? 1L: 0L);
 
        String icontext = entity.getIcontext();
        if (icontext != null) {
            stmt.bindString(34, icontext);
        }
 
        String category_slug = entity.getCategory_slug();
        if (category_slug != null) {
            stmt.bindString(35, category_slug);
        }

        String love_cover = entity.getLove_cover();
        if (love_cover != null) {
            stmt.bindString(36, love_cover);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LiveInfo readEntity(Cursor cursor, int offset) {
        LiveInfo entity = new LiveInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // no
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // nick
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // avatar
            cursor.getInt(offset + 3), // follow
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // id
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // uid
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // title
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // category_id
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // slug
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // intro
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // announcement
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // cover
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // play_at
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // last_play_at
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // view
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // status
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // priv
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // landscape
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // position
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // weight
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // check
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // recommend_image
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // videoQuality
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // category_name
            cursor.getInt(offset + 24), // screen
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // start_time
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // stream
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // thumb
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // video
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // app_shuffling_image
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // categoryId
            cursor.getShort(offset + 31) != 0, // hidden
            cursor.getShort(offset + 32) != 0, // play_status
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // icontext
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // category_slug
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35) // love_conver
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LiveInfo entity, int offset) {
        entity.setNo(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setNick(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAvatar(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFollow(cursor.getInt(offset + 3));
        entity.setId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUid(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTitle(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCategory_id(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSlug(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIntro(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAnnouncement(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCover(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setPlay_at(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setLast_play_at(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setView(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setStatus(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setPriv(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setLandscape(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setPosition(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setWeight(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setCheck(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setRecommend_image(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setVideoQuality(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setCategory_name(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setScreen(cursor.getInt(offset + 24));
        entity.setStart_time(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setStream(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setThumb(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setVideo(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setApp_shuffling_image(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setCategoryId(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setHidden(cursor.getShort(offset + 31) != 0);
        entity.setPlay_status(cursor.getShort(offset + 32) != 0);
        entity.setIcontext(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setCategory_slug(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setLove_cover(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LiveInfo entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LiveInfo entity) {
        return null;
    }

    @Override
    public boolean hasKey(LiveInfo entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
