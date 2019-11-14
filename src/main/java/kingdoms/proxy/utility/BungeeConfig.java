package kingdoms.proxy.utility;

import java.io.*;
import java.util.logging.Level;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

public class BungeeConfig {

    private static final ConfigurationProvider provider = ConfigurationProvider.getProvider(net.md_5.bungee.config.YamlConfiguration.class);
    private final Plugin plugin;
    private final File file;
    private Configuration config;
    public BungeeConfig(Plugin plugin){
        this(plugin, "taber.yml");
    }

    public BungeeConfig(Plugin plugin, String name){
        this(plugin, new File(plugin.getDataFolder(), name));
    }

    public BungeeConfig(Plugin plugin, File file) {
        this.plugin = plugin;
        this.file = file;
        load();
    }

    public void load(){
        if(!this.file.getParentFile().exists()){
            this.file.getParentFile().mkdirs();
        }
        if(!this.file.exists()) {

            try{
                this.file.createNewFile();
            } catch (IOException e){
                this.plugin.getLogger().log(Level.SEVERE, String.format("Could not create the configuration file '%s'.", new Object[] {this.file.getName() }), e);
            }
            try (InputStream in = this.plugin.getResourceAsStream(this.file.getName()); OutputStream out = new FileOutputStream(this.file)){
                ByteStreams.copy(in, out);
            } catch (FileNotFoundException e){
                this.plugin.getLogger().log(Level.SEVERE, String.format("Configuration file '%s' not found.", new Object[] {this.file.getName() }), e);
            } catch (IOException e){
                this.plugin.getLogger().log(Level.SEVERE, String.format("Could not copy defaults to file '%s'.", new Object[] {this.file.getName() }), e);
            }
        }

       try{
           this.config = provider.load(this.file);
       } catch (IOException e){
           this.plugin.getLogger().log(Level.SEVERE, String.format("Could not load configuration '%s'.", new Object[] { this.file.getName() }), e);
       }
    }

    public void save(){
        try{
            provider.save(this.config, this.file);
        } catch (IOException e){
            this.plugin.getLogger().log(Level.SEVERE, String.format("Could not save configuration file '%s'.", new Object[] { this.file.getName() }), e);
        }
    }

    public Configuration getConfig(){
        return this.config;
    }

    public File getFile(){
        return this.file;
    }

}
