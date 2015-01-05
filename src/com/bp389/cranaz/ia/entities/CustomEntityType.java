package com.bp389.cranaz.ia.entities;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_8_R1.BiomeBase;
import net.minecraft.server.v1_8_R1.BiomeMeta;
import net.minecraft.server.v1_8_R1.EntityCaveSpider;
import net.minecraft.server.v1_8_R1.EntityCreeper;
import net.minecraft.server.v1_8_R1.EntityEnderman;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntitySkeleton;
import net.minecraft.server.v1_8_R1.EntitySlime;
import net.minecraft.server.v1_8_R1.EntitySpider;
import net.minecraft.server.v1_8_R1.EntityTypes;
import net.minecraft.server.v1_8_R1.EntityWitch;
import net.minecraft.server.v1_8_R1.EntityZombie;

import org.bukkit.entity.EntityType;

/**
 * Classe permettant d'enregistrer par NMS toutes les entit�s modifi�es
 * @author BlackPhantom
 *
 */
public enum CustomEntityType {

	ZOMBIE("Zombie", 54, EntityType.ZOMBIE, EntityZombie.class, EnhancedZombie.class),
	SKELETON("Skeleton", 51, EntityType.SKELETON, EntitySkeleton.class, UnspawnableSkeleton.class), 
	SPIDER("Spider", 52, EntityType.SPIDER, EntitySpider.class, UnspawnableSpider.class),
	CREEPER("Creeper", 50, EntityType.CREEPER, EntityCreeper.class, UnspawnableCreeper.class), 
	ENDERMAN("Enderman", 58, EntityType.ENDERMAN, EntityEnderman.class, UnspawnableEnderman.class),
	WITCH("Witch", 66, EntityType.WITCH, EntityWitch.class, UnspawnableWitch.class),
	SLIME("Slime", 55, EntityType.SLIME, EntitySlime.class, UnspawnableSlime.class),
	CAVE_SPIDER("CaveSpider", 59, EntityType.CAVE_SPIDER, EntityCaveSpider.class, UnspawnableCaveSpider.class);

	private String name;
	private int id;
	private EntityType entityType;
	private Class<? extends EntityInsentient> nmsClass;
	private Class<? extends EntityInsentient> customClass;

	private CustomEntityType(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
		this.name = name;
		this.id = id;
		this.entityType = entityType;
		this.nmsClass = nmsClass;
		this.customClass = customClass;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public Class<? extends EntityInsentient> getNMSClass() {
		return nmsClass;
	}

	public Class<? extends EntityInsentient> getCustomClass() {
		return customClass;
	}

	/**
	 * Register our entities.
	 */
	public static void registerEntities() {
		for (CustomEntityType entity : values()) /*Get our entities*/
			a(entity.getCustomClass(), entity.getName(), entity.getID());
		/*Get all biomes on the server*/
		BiomeBase[] biomes;
		try {
			biomes = (BiomeBase[]) getPrivateStatic(BiomeBase.class, "biomes");
		} catch (Exception exc) {
			return;
		}
		for (BiomeBase biomeBase : biomes) {
			if (biomeBase == null)
				break;
			for (String field : new String[] { "at", "au", "av", "aw" }) //Lists that hold all entity types
				try {
					Field list = BiomeBase.class.getDeclaredField(field);
					list.setAccessible(true);
					@SuppressWarnings("unchecked")
					List<BiomeMeta> mobList = (List<BiomeMeta>) list.get(biomeBase);

					for (BiomeMeta meta : mobList)
						for (CustomEntityType entity : values())
							if (entity.getNMSClass().equals(meta.b)) /*Test if the entity has the custom entity type*/
								meta.b = entity.getCustomClass(); //Set it's meta to our custom class's meta
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * Unregister our entities to prevent memory leaks. Call on disable.
	 */
	@SuppressWarnings("rawtypes")
	public static void unregisterEntities() {
		for (CustomEntityType entity : values()) {
			// Remove our class references.
			try {
				((Map) getPrivateStatic(EntityTypes.class, "d")).remove(entity.getCustomClass());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				((Map) getPrivateStatic(EntityTypes.class, "f")).remove(entity.getCustomClass());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (CustomEntityType entity : values())
			try {
				a(entity.getNMSClass(), entity.getName(), entity.getID());
			} catch (Exception e) {
				e.printStackTrace();
			}

		BiomeBase[] biomes;
		try {
			biomes = (BiomeBase[]) getPrivateStatic(BiomeBase.class, "biomes"); /*Get all biomes again*/
		} catch (Exception exc) {
			return;
		}
		for (BiomeBase biomeBase : biomes) {
			if (biomeBase == null)
				break;

			for (String field : new String[] { "at", "au", "av", "aw" }) /*The entity list*/
				try {
					Field list = BiomeBase.class.getDeclaredField(field);
					list.setAccessible(true);
					@SuppressWarnings("unchecked")
					List<BiomeMeta> mobList = (List<BiomeMeta>) list.get(biomeBase);

					for (BiomeMeta meta : mobList)
						for (CustomEntityType entity : values())
							if (entity.getCustomClass().equals(meta.b))
								meta.b = entity.getNMSClass(); /*Set the entities meta back to the NMS one*/
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * A convenience method.
	 * 
	 * @param clazz
	 *            The class.
	 * @param f
	 *            The string representation of the private static field.
	 * @return The object found
	 * @throws Exception
	 *             if unable to get the object.
	 */
	@SuppressWarnings("rawtypes")
	private static Object getPrivateStatic(Class clazz, String f)
			throws Exception {
		Field field = clazz.getDeclaredField(f);
		field.setAccessible(true);
		return field.get(null);
	}

	/*
	 * Since 1.7.2 added a check in their entity registration, simply bypass it
	 * and write to the maps ourself.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void a(Class paramClass, String paramString, int paramInt) {
		try {
			((Map) getPrivateStatic(EntityTypes.class, "c")).put(paramString,
					paramClass);
			((Map) getPrivateStatic(EntityTypes.class, "d")).put(paramClass,
					paramString);
			((Map) getPrivateStatic(EntityTypes.class, "e")).put(
					Integer.valueOf(paramInt), paramClass);
			((Map) getPrivateStatic(EntityTypes.class, "f")).put(paramClass,
					Integer.valueOf(paramInt));
			((Map) getPrivateStatic(EntityTypes.class, "g")).put(paramString,
					Integer.valueOf(paramInt));
		} catch (Exception exc) {
			// Unable to register the new class.
		}
	}
}