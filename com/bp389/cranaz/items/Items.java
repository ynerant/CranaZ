package com.bp389.cranaz.items;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Classe contenant divers objets modifi�s au consensus de CranaZ,
 * ainsi que de nouvelles recettes
 * @author BlackPhantom
 *
 */
public  class Items
{
	public static Random random = new Random();
	public static final Material BAG = Material.QUARTZ;
	private static JavaPlugin jp;
	public static final void init(JavaPlugin plugin){jp = plugin;}
	public static void recipes()
	{
		ArrayList<ShapedRecipe> shapes = new ArrayList<ShapedRecipe>();
		ArrayList<ShapelessRecipe> unshapes = new ArrayList<ShapelessRecipe>();
		ShapedRecipe machette = new ShapedRecipe(customISword());
		machette.shape(" F ", " F ", " B ");
		machette.setIngredient('F', Material.IRON_INGOT);
		machette.setIngredient('B', Material.STICK);
		shapes.add(machette);
		
		ShapedRecipe machette1 = new ShapedRecipe(customISword());
		machette1.shape("F  ", "F  ", "B  ");
		machette1.setIngredient('F', Material.IRON_INGOT);
		machette1.setIngredient('B', Material.STICK);
		shapes.add(machette1);
		
		ShapedRecipe machette2 = new ShapedRecipe(customISword());
		machette2.shape("  F", "  F", "  B");
		machette2.setIngredient('F', Material.IRON_INGOT);
		machette2.setIngredient('B', Material.STICK);
		shapes.add(machette2);
		
		ShapedRecipe sac = new ShapedRecipe(bagItemStack());
		sac.shape("   ", "CKC", "CCC");
		sac.setIngredient('C', Material.LEATHER);
		sac.setIngredient('K', Material.CHEST);
		shapes.add(sac);
		
		ShapelessRecipe amphet = new ShapelessRecipe(customPPie());
		amphet.addIngredient(Material.POTION);
		amphet.addIngredient(Material.SUGAR);
		amphet.addIngredient(Material.REDSTONE);
		amphet.addIngredient(Material.FERMENTED_SPIDER_EYE);
		amphet.addIngredient(Material.RED_MUSHROOM);
		unshapes.add(amphet);
		
		ShapelessRecipe neurotoxic = new ShapelessRecipe(Items.Subs.Poison.NEUROTOXIC.toItem());
		neurotoxic.addIngredient(Material.POTION);
		neurotoxic.addIngredient(Material.FERMENTED_SPIDER_EYE);
		neurotoxic.addIngredient(Material.RED_MUSHROOM);
		neurotoxic.addIngredient(Material.CARROT);
		unshapes.add(neurotoxic);
		
		ShapedRecipe blouse = new ShapedRecipe(hospitalShirt());
		blouse.shape("C C", "CLC", "CCC");
		blouse.setIngredient('C', Material.LEATHER);
		blouse.setIngredient('L', Material.WOOL);
		shapes.add(blouse);
		
		ShapedRecipe shirt = new ShapedRecipe(genTShirt(new ItemStack(Material.LEATHER_CHESTPLATE)));
		shirt.shape("C C", "CCC", "CCC");
		shirt.setIngredient('C', Material.LEATHER);
		shapes.add(shirt);
		
		ShapedRecipe pant = new ShapedRecipe(genTShirt(new ItemStack(Material.LEATHER_LEGGINGS)));
		pant.shape("CCC", "C C", "C C");
		pant.setIngredient('C', Material.LEATHER);
		shapes.add(pant);
		
		ShapedRecipe hat = new ShapedRecipe(genTShirt(new ItemStack(Material.LEATHER_HELMET)));
		hat.shape("CCC", "C C", "   ");
		hat.setIngredient('C', Material.LEATHER);
		shapes.add(hat);
		
		ShapedRecipe hat2 = new ShapedRecipe(genTShirt(new ItemStack(Material.LEATHER_HELMET)));
		hat2.shape("   ", "CCC", "C C");
		hat2.setIngredient('C', Material.LEATHER);
		shapes.add(hat2);
		
		ShapedRecipe boots = new ShapedRecipe(genTShirt(new ItemStack(Material.LEATHER_BOOTS)));
		boots.shape("C C", "C C", "   ");
		boots.setIngredient('C', Material.LEATHER);
		shapes.add(boots);
		
		ShapedRecipe boots2 = new ShapedRecipe(genTShirt(new ItemStack(Material.LEATHER_BOOTS)));
		boots2.shape("   ", "C C", "C C");
		boots2.setIngredient('C', Material.LEATHER);
		shapes.add(boots2);
		
		ShapedRecipe iAxe = new ShapedRecipe(customIAxe());
		iAxe.shape(" FF", " BF", " B ");
		iAxe.setIngredient('B', Material.STICK);
		iAxe.setIngredient('F', Material.IRON_INGOT);
		shapes.add(iAxe);
		
		ShapedRecipe bow = new ShapedRecipe(customBow());
		bow.shape("FB ", "F B", "FB ");
		bow.setIngredient('F', Material.STRING);
		bow.setIngredient('B', Material.STICK);
		shapes.add(bow);
		/*
		 * 
		 */
		for(int i = 0;i < shapes.size();i++){
			Bukkit.getServer().addRecipe(shapes.get(i));
		}
		for(int i = 0;i < unshapes.size();i++){
			Bukkit.getServer().addRecipe(unshapes.get(i));
		}
	}
	public static ItemStack customSSword(){
		ItemStack is = new ItemStack(Material.STONE_SWORD);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Massue");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Pour zigouiller du macchab� !" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack getWrittenBook(final String title, final String dotSplitLore, final String... pages){
		ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bm = (BookMeta)is.getItemMeta();
		bm.setPages(pages);
		bm.setTitle(title);
		bm.setLore(Arrays.asList(dotSplitLore.split(".")));
		is.setItemMeta(bm);
		return is;
	}
	public static ItemStack bagItemStack(){
		ItemStack temp = new ItemStack(BAG);
		ItemMeta im = temp.getItemMeta();
		im.setDisplayName(ChatColor.BOLD + "Sac a dos" + ChatColor.RESET);
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Permet de porter des objets.", ChatColor.ITALIC + "Shift + clic droit pour l'ouvrir" + ChatColor.RESET));
		temp.setItemMeta(im);
		return temp;
	}
	public static ItemStack customIAxe(){
		ItemStack is = new ItemStack(Material.IRON_AXE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Hache");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Bien aiguisee en plus !" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customISword(){
		ItemStack is = new ItemStack(Material.IRON_SWORD);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Machette");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Fais gaffe a ta main !" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customBow(){
		ItemStack is = new ItemStack(Material.BOW);
		ItemMeta im = is.getItemMeta();
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Pour tuer du zombie en toute discretion !" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack getAmmoStack(ItemStack from){
		ItemMeta im = from.getItemMeta();
		ItemStack temp = from;
		if(from.getType() == Material.BLAZE_POWDER){
			im.setDisplayName("Balles de Mosin Nagant");
			im.setLore(Arrays.asList(ChatColor.ITALIC + "Balles pour fusil de sniper type Mosin", ChatColor.ITALIC + "Rechargement balle par balle" + ChatColor.RESET));
			temp.setItemMeta(im);
			return temp;
		}
		else if(from.getType() == Material.GOLD_NUGGET){
			im.setDisplayName("Chargeurs d'AK-47");
			im.setLore(Arrays.asList(ChatColor.ITALIC + "Chargeurs pour fusil d'assaut type AK-47" + ChatColor.RESET));
			temp.setItemMeta(im);
			return temp;
		}
		else if(from.getType() == Material.FIREBALL){
			im.setDisplayName("Grenades de M320H");
			im.setLore(Arrays.asList(ChatColor.ITALIC + "Grenades pour lance-grenades type M320H", ChatColor.ITALIC + "Une seule grenade par chambre" + ChatColor.RESET));
			temp.setItemMeta(im);
			return temp;
		}
		else if(from.getType() == Material.MAGMA_CREAM){
			im.setDisplayName("Chargeurs de BAR Browning");
			im.setLore(Arrays.asList(ChatColor.ITALIC + "Chargeurs pour fusil mitrailleur type BAR" + ChatColor.RESET));
			temp.setItemMeta(im);
			return temp;
		}
		else if(from.getType() == Material.SLIME_BALL){
			im.setDisplayName("Balles de Smith");
			im.setLore(Arrays.asList(ChatColor.ITALIC + "Balles pour Colt type Smith" + ChatColor.RESET));
			temp.setItemMeta(im);
			return temp;
		}
		return temp;
	}
	public static ItemStack customPaper()
	{
		ItemStack is = new ItemStack(Material.PAPER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Bandages");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Ca peut servir..." + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customGApple()
	{
		ItemStack is = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Grande poche de sang");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "On transfuse ?" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customApple()
	{
		ItemStack is = new ItemStack(Material.APPLE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Petite poche de sang");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "On transfuse ?" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customPPie()
	{
		ItemStack is = new ItemStack(Material.PUMPKIN_PIE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Amphetamines");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "C'est de la bonne !" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customCamo()
	{
		ItemStack is = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Tenue de camouflage - Plastron");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Permet d'etre vu de moins loin. - 15 %" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customCamo_helmet(){
		ItemStack is = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Tenue de camouflage - Casque");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Permet d'etre vu de moins loin. - 5 %" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customCamo_boots(){
		ItemStack is = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Tenue de camouflage - Bottes");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Permet d'etre vu de moins loin. - 20 %" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack customCamo_pants(){
		ItemStack is = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Tenue de camouflage - Pantalon");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Permet d'etre vu de moins loin. - 20 %" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;

	}
	public static ItemStack hospitalShirt(){
		ItemStack is = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta im = (LeatherArmorMeta)is.getItemMeta();
		im.setDisplayName("Blouse");
		im.setColor(Color.WHITE);
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack genTShirt(ItemStack lFrom)
	{
		ItemStack temp = lFrom;
		LeatherArmorMeta lam = (LeatherArmorMeta)temp.getItemMeta();
		Color c = Color.MAROON;
		switch(temp.getType())
		{
		case LEATHER_CHESTPLATE:
			lam.setDisplayName("T-Shirt");
			break;
		case LEATHER_LEGGINGS:
			lam.setDisplayName("Pantalon");
			break;
		case LEATHER_BOOTS:
			lam.setDisplayName("Bottes");
			break;
		case LEATHER_HELMET:
			lam.setDisplayName("Casquette");
			break;
		}
		switch(random.nextInt(17))
		{
		case 0:
			c = Color.AQUA;
			break;
		case 1:
			c = Color.BLACK;
			break;
		case 2:
			c = Color.BLUE;
			break;
		case 3:
			c = Color.FUCHSIA;
			break;
		case 4:
			c = Color.GRAY;
			break;
		case 5:
			c = Color.GREEN;
			break;
		case 6:
			c = Color.LIME;
			break;
		case 7:
			c = Color.MAROON;
			break;
		case 8:
			c = Color.NAVY;
			break;
		case 9:
			c = Color.OLIVE;
			break;
		case 10:
			c = Color.ORANGE;
			break;
		case 11:
			c = Color.PURPLE;
			break;
		case 12:
			c = Color.RED;
			break;
		case 13:
			c = Color.SILVER;
			break;
		case 14:
			c = Color.TEAL;
			break;
		case 15:
			c = Color.WHITE;
			break;
		case 16:
			c = Color.YELLOW;
			break;
		}
		lam.setColor(c);
		temp.setItemMeta(lam);
		return temp;
	}
	public static ItemStack customWater()
	{
		ItemStack is = new ItemStack(Material.POTION);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Bouteille d'eau");
		im.setLore(Arrays.asList(ChatColor.ITALIC + "Pour tous les types de soif !" + ChatColor.RESET));
		is.setItemMeta(im);
		return is;
	}
	public static class Subs
	{
		public static enum Drugs
		{
			AMPHETAMIN(customPPie());
			private ItemStack theIS;
			Drugs(ItemStack i){
				this.theIS = i;
			}
			public ItemStack toItem(){
				return theIS;
			}
			public boolean compareTo(ItemStack another){
				ItemMeta im = theIS.getItemMeta(), im2 = another.getItemMeta();
				return im.getDisplayName().equalsIgnoreCase(im2.getDisplayName());
			}
		}
		public static enum Poison
		{
			NEUROTOXIC(neurotoxic()),
			ARTERIAL(arterial());
			private ItemStack theIS;
			Poison(ItemStack i){
				this.theIS = i;
			}
			public ItemStack toItem(){
				return theIS;
			}
			public boolean compareTo(ItemStack another){
				ItemMeta im = theIS.getItemMeta(), im2 = another.getItemMeta();
				return im.getDisplayName().equalsIgnoreCase(im2.getDisplayName());
			}
			private static ItemStack arterial()
			{
				ItemStack is = new ItemStack(Material.PUMPKIN_PIE);
				ItemMeta im = is.getItemMeta();
				im.setDisplayName("Poison arteriel");
				im.setLore(Arrays.asList("Augmente la pression sanguine de la cible.",
						"Cause des dommages directs moyens",
						"Fait effet apr�s 45 secondes."));
				is.setItemMeta(im);
				return is;
			}
			private static ItemStack neurotoxic()
			{
				ItemStack is = new ItemStack(Material.PUMPKIN_PIE);
				ItemMeta im = is.getItemMeta();
				im.setDisplayName("Poison neurotoxique");
				im.setLore(Arrays.asList("Affaiblit fortement le joueur cible.",
						"Ne cause AUCUN dommage direct.",
						"Fait effet 2 minutes apr�s injection."));
				is.setItemMeta(im);
				return is;
			}
		}
	}
	public static class Diaries
	{
		public interface DiaryEnum
		{
			public ItemStack toItemStack();
			public String[] pages();
			public String title();
		}
		public enum Utils implements DiaryEnum
		{
	        RULES("R�gles", "Staff de CranaZ", getRules()),
	        STAFF("Membres du staff", "Staff de CranaZ", Arrays.asList("Membres du staff de CranaZ:\n\n" +
	        		"- Arnialo -> Responsable de projet, graphiste.\n" +
	        		"- Manercraft -> Responsable de projet et architecte.\n" +
	        		"- BlackPhantom -> D�veloppeur, testeur (c'est moi !).\n",
	        		"- TheFOXgAME -> Builder.\n" +
	    	        "- Alastar -> Chef de construction, builder.\n" +
	    	        "...")),
	        SURVIVE("Guide de survie I", "Staff de CranaZ", getSurvive1()),
	        CRAFTS("Crafter sereinement", "BlackPhantom389", Arrays.asList("Dans un monde impitoyable," +
	        		" il est important de savoir comment cr�er soi-m�me ce dont l'on a besoin." +
	        		" Je suis s�r que vous connaissez d�j� beaucoup de choses, mais dans ce monde," +
	        		" beaucoup de choses sont uniques.",
	        		"Vous trouverez sur les pages suivantes" +
	        		" une liste(� peu pr�s exhaustive)des plans de fabrication" +
	        		" de divers objets.",
	        		"Amph�tamines\n" +
	        		"Leur craft est relativement complexe, mais n'est pas ordonn�.\n" +
	        		"Ingr�dients:\n" +
	        		"- Eau\n" +
	        		"- Sucre\n" +
	        		"- Redstone\n" +
	        		"- Champignon(petit)\n" +
	        		"- Oeil d'araign�e pourri\n",
	        		"Sac � dos\n" +
	        		"N�cessite beaucoup de cuir. Craft ordonn�\n" +
	        		"Plan du craft:\n" +
	        		"C0C   C = Cuir\n" +
	        		"CKC   K = Coffre\n" +
	        		"CCC   0 = Vide\n",
	        		"Poison neurotoxique:\n" +
	        		"Craft complexe. Non ordonn�.\n" +
	        		"Ingr�dients:\n" +
	        		"- Eau\n" +
	        		"- Oeil d'araign�e pourri\n" +
	        		"- Champignon(petit)\n" +
	        		"- Carotte"));
	        @Override
            public ItemStack toItemStack() {
	            return theBook;
            }

			@Override
            public String[] pages() {
	            return (String[])theMeta.getPages().toArray();
            }

			@Override
            public String title() {
	            return theMeta.getTitle();
            }
			private ItemStack theBook;
			private BookMeta theMeta;
			Utils(String title, String author, List<String> pages){
				theBook = new ItemStack(Material.WRITTEN_BOOK);
				theMeta = (BookMeta)theBook.getItemMeta();
				theMeta.setTitle(title);
				theMeta.setAuthor(author);
				theMeta.setPages(pages);
				theBook.setItemMeta(theMeta);
			}
			private static List<String> getSurvive1(){
				FileConfiguration fc = jp.getConfig();
				try {
	                fc.load(new File("plugins/CranaZ/Divers/survie.yml"));
                } catch (IOException | InvalidConfigurationException e) {}
				return fc.getStringList("survie");
			}
			private static List<String> getRules(){
				FileConfiguration fc = jp.getConfig();
				try {
	                fc.load(new File("plugins/CranaZ/Divers/regles.yml"));
                } catch (IOException | InvalidConfigurationException e) {}
				return fc.getStringList("regles");
			}
			public static void giveUtils(Player p){
				p.getInventory().addItem(Utils.RULES.toItemStack(), Utils.STAFF.toItemStack(), Utils.SURVIVE.toItemStack());
			}
		}
		public enum Bedroom implements DiaryEnum
		{
			JORDAN_KENS("Journal de Jordan - Chapitre 1", "Jordan Kens", Arrays.asList("08/07/2014\n\n" +
					"Une nouvelle maladie semble prendre forme.\n" +
					"Elle d�coule de la rage, cela m'inqui�te.\n" +
					"En tout cas, ils en parlent partout.\n" +
					"On verra bien.",
					"28/07/2014\n\n" +
					"La maladie semble se propager. Mon p�re se m�fie,\n" +
					"il dit que c'est encore un coup du gouvernement.\n" +
					"Je ne sais pas quoi penser.",
					"11/09/2014\n\n" +
					"La maladie a mut� et est maintenant grave,\n" +
					"et mon p�re, fervant adepte de l'apocalypse zombie,\n" +
					"vient de rentrer de l'armurerie. Il a achet� un Mosin\n" +
					"et des balles. Nous �sperons ne pas avoir � nous en servir.",
					"20/11/2014\n\n" +
					"Nous nous terrons maintenant dans notre maison depuis ma derni�re\n" +
					"page de journal. Les zombies prennent notre maison d'assaut,\n" +
					"mais mon p�re avait pr�vu le coup. J'esp�re que nous aurons de quoi manger.",
					"12/02/2015\n\n" +
					"Beaucoup d'humains sont infect�s.\n" +
					"Nous allons d�menager sur une �le o� nous serons tranquilles pendant que\n" +
					"ces saloperies pourrissent.",
					"20/05/2017\n\n" +
					"Je viens d'avoir 16 ans, et je vais apprendre � tirer." +
					"J'aurai d� prendre mon arc, mais je l'ai laiss� dans notre ancienne maison." +
					"Les cadavres de zombies s'empilent devant la maison. J'en ai la naus�e.",
					"18/07/2020\n\n" +
					"Ils travaillent sur un antidote, j'�sp�re qu'ils y arriveront.",
					"22/01/2022\n\n" +
					"Ils ont presque fini leur recherche. Vivement qu'ils trouvent\n" +
					"la formule, les balles manquent.",
					"27/02/2022\n\n" +
					"Je commence � plaider l'hyposth�se du suicide collectif.\n" +
					"Les zombies se sont infiltr�s dans notre complexe suite �\n" +
					"une nouvelle mutation. Ils ont tu� les scientifiques et\n" +
					"l'espoir d'antidote est quasiment nul.",
					"15/08/2024\n\n" +
					"Je n'en peux plus. C'est trop dur. Les balles manquent,\n" +
					"on cr�ve de faim, et comme je vais crever bient�t, je peux le\n" +
					"dire, j'en peux plus d'�tre puceau.",
					"07/04/2025\n\n" +
					"Nous ne sommes plus assez. Les balles sont si rares...\n" +
					"C'est la derni�re fois que j'�cris, je vais mourir bient�t.\n" +
					"Adieu."));
			private ItemStack theBook;
			private BookMeta theMeta;
			Bedroom(String title, String author, List<String> pages){
				theBook = new ItemStack(Material.WRITTEN_BOOK);
				theMeta = (BookMeta)theBook.getItemMeta();
				theMeta.setTitle(title);
				theMeta.setAuthor(author);
				theMeta.setPages(pages);
				theBook.setItemMeta(theMeta);
			}
			@Override
            public ItemStack toItemStack() {
	            return theBook;
            }

			@Override
            public String[] pages() {
	            return (String[])theMeta.getPages().toArray();
            }

			@Override
            public String title() {
	            return theMeta.getTitle();
            }
		}
	}
}