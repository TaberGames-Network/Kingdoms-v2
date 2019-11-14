package kingdoms.proxy;

import kingdoms.proxy.utility.BungeeConfig;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.security.Security;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Kingdomsv2 extends Plugin implements Listener {


    //Defining all hashmaps and strings, whether public or private.
    public static final String noIconURL = "https://kingdoms.www1.gocadia.co/images/servericons/blank.png";
    public static final String offlineKingdomURL = "https://kingdoms.www1.gocadia.co/images/servericons/offline.png";
    private static Kingdomsv2 instance;
    private ConcurrentHashMap<String, String> kingdomsMods = new ConcurrentHashMap();
    public ConcurrentHashMap<String, String> getKingdomsMods() {
        return this.kingdomsMods;
    }
    private List<String> hubServers = new CopyOnWriteArrayList();
    public List<String> getHubServers() {
        return this.hubServers;
    }
    private AtomicBoolean serverTaskRunning = new AtomicBoolean(false);
    public AtomicBoolean getServerTaskRunning() {
        return this.serverTaskRunning;
    }
    private AtomicBoolean permissionTaskRunning = new AtomicBoolean(false);
    public AtomicBoolean getPermissionTaskRunning(){
        return this.permissionTaskRunning;
    }
    private ConcurrentHashMap<String, String> lastServer = new ConcurrentHashMap();

    public ConcurrentHashMap<String, String> getLastServer() {
        return lastServer;
    }

    //public KingdomsServerInfo getRandomHubServer() {
    //    int indexx = getRandom().nextInt(getHubServers().size());
    //    return (KingdomsServerInfo)BungeeCord.getInstance().getServerInfo((String)getHubServers().get(indexx));
    //}
    private Random random = new Random();
    public Random getRandom() {
        return this.random;
    }
    private BungeeConfig config;
    private Boolean isReady = Boolean.valueOf(false);
    public Boolean getIsReady() {
        return this.isReady;
    }

    public static Kingdomsv2 getInstance(){
        return instance;
    }

    public void onDisable(){
        getProxy().getScheduler().cancel(getInstance());
        //MySQL.closeConnections();
    }
    public void onEnable(){
        Security.setProperty("networkaddress.cache.ttl", "30");
        Security.setProperty("sun.net.inetaddr.negative.ttl", "10");
        instance = this;
        this.config = new BungeeConfig(getInstance());
        this.config.load();

        //getProxy().getPluginManager().registerListener(getInstance(), new Chat());
    }

}
