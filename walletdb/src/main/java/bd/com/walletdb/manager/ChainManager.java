package bd.com.walletdb.manager;

import java.util.List;

import bd.com.walletdb.action.ChainAction;
import bd.com.walletdb.entity.ChainEntity;
import bd.com.walletdb.greendao.ChainEntityDao;

public class ChainManager {

    private static ChainManager manager = new ChainManager();

    private ChainManager() {
    }    

    public static ChainManager getManager() {
        return manager;
    }
}
