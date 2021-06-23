package noobanidus.mods.timetodie;

import net.minecraft.item.NameTagItem;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.noobutil.registrate.CustomRegistrate;
import noobanidus.mods.timetodie.init.ModEffects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TimeToDie.MODID)
public class TimeToDie {
  public static final String MODID = "timetodie";
  public static CustomRegistrate REGISTRATE;
  public static Logger LOG = LogManager.getLogger();

  public TimeToDie() {
    REGISTRATE = CustomRegistrate.create(MODID);

/*    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);*/
    /*    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();*/
    /*    bus.addListener(ModSetup::init);*/

    ModEffects.load();
  }
}
