package org.example.models;

import java.util.List;

public class ResponseItem{
	private int complexity;
	private String gender;
	private int skin;
	private Description description;
	private boolean prereleaseOnly;
	private Colors colors;
	private ItemDraftBucketing itemDraftBucketing;
	private PopularItems popularItems;
	private ShopStatDisplay shopStatDisplay;
	private Physics physics;
	private String gunTag;
	private boolean disabled;
	private String heroType;
	private LevelInfo levelInfo;
	private int id;
	private String searchName;
	private String className;
	private StatsDisplay statsDisplay;
	private ItemSlotInfo itemSlotInfo;
	private boolean assignedPlayersOnly;
	private boolean limitedTesting;
	private Models models;
	private Images images;
	private CostBonuses costBonuses;
	private HeroStatsUi heroStatsUi;
	private ScalingStats scalingStats;
	private StartingStats startingStats;
	private String developmentState;
	private boolean inDevelopment;
	private StandardLevelUpUpgrades standardLevelUpUpgrades;
	private List<String> tags;
	private PurchaseBonuses purchaseBonuses;
	private String name;
	private boolean playerSelectable;
	private boolean needsTesting;
	private String hideoutRichPresence;
	private Items items;

	public void setComplexity(int complexity){
		this.complexity = complexity;
	}

	public int getComplexity(){
		return complexity;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSkin(int skin){
		this.skin = skin;
	}

	public int getSkin(){
		return skin;
	}

	public void setDescription(Description description){
		this.description = description;
	}

	public Description getDescription(){
		return description;
	}

	public void setPrereleaseOnly(boolean prereleaseOnly){
		this.prereleaseOnly = prereleaseOnly;
	}

	public boolean isPrereleaseOnly(){
		return prereleaseOnly;
	}

	public void setColors(Colors colors){
		this.colors = colors;
	}

	public Colors getColors(){
		return colors;
	}

	public void setItemDraftBucketing(ItemDraftBucketing itemDraftBucketing){
		this.itemDraftBucketing = itemDraftBucketing;
	}

	public ItemDraftBucketing getItemDraftBucketing(){
		return itemDraftBucketing;
	}

	public void setPopularItems(PopularItems popularItems){
		this.popularItems = popularItems;
	}

	public PopularItems getPopularItems(){
		return popularItems;
	}

	public void setShopStatDisplay(ShopStatDisplay shopStatDisplay){
		this.shopStatDisplay = shopStatDisplay;
	}

	public ShopStatDisplay getShopStatDisplay(){
		return shopStatDisplay;
	}

	public void setPhysics(Physics physics){
		this.physics = physics;
	}

	public Physics getPhysics(){
		return physics;
	}

	public void setGunTag(String gunTag){
		this.gunTag = gunTag;
	}

	public String getGunTag(){
		return gunTag;
	}

	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}

	public boolean isDisabled(){
		return disabled;
	}

	public void setHeroType(String heroType){
		this.heroType = heroType;
	}

	public String getHeroType(){
		return heroType;
	}

	public void setLevelInfo(LevelInfo levelInfo){
		this.levelInfo = levelInfo;
	}

	public LevelInfo getLevelInfo(){
		return levelInfo;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSearchName(String searchName){
		this.searchName = searchName;
	}

	public String getSearchName(){
		return searchName;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	public void setStatsDisplay(StatsDisplay statsDisplay){
		this.statsDisplay = statsDisplay;
	}

	public StatsDisplay getStatsDisplay(){
		return statsDisplay;
	}

	public void setItemSlotInfo(ItemSlotInfo itemSlotInfo){
		this.itemSlotInfo = itemSlotInfo;
	}

	public ItemSlotInfo getItemSlotInfo(){
		return itemSlotInfo;
	}

	public void setAssignedPlayersOnly(boolean assignedPlayersOnly){
		this.assignedPlayersOnly = assignedPlayersOnly;
	}

	public boolean isAssignedPlayersOnly(){
		return assignedPlayersOnly;
	}

	public void setLimitedTesting(boolean limitedTesting){
		this.limitedTesting = limitedTesting;
	}

	public boolean isLimitedTesting(){
		return limitedTesting;
	}

	public void setModels(Models models){
		this.models = models;
	}

	public Models getModels(){
		return models;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public Images getImages(){
		return images;
	}

	public void setCostBonuses(CostBonuses costBonuses){
		this.costBonuses = costBonuses;
	}

	public CostBonuses getCostBonuses(){
		return costBonuses;
	}

	public void setHeroStatsUi(HeroStatsUi heroStatsUi){
		this.heroStatsUi = heroStatsUi;
	}

	public HeroStatsUi getHeroStatsUi(){
		return heroStatsUi;
	}

	public void setScalingStats(ScalingStats scalingStats){
		this.scalingStats = scalingStats;
	}

	public ScalingStats getScalingStats(){
		return scalingStats;
	}

	public void setStartingStats(StartingStats startingStats){
		this.startingStats = startingStats;
	}

	public StartingStats getStartingStats(){
		return startingStats;
	}

	public void setDevelopmentState(String developmentState){
		this.developmentState = developmentState;
	}

	public String getDevelopmentState(){
		return developmentState;
	}

	public void setInDevelopment(boolean inDevelopment){
		this.inDevelopment = inDevelopment;
	}

	public boolean isInDevelopment(){
		return inDevelopment;
	}

	public void setStandardLevelUpUpgrades(StandardLevelUpUpgrades standardLevelUpUpgrades){
		this.standardLevelUpUpgrades = standardLevelUpUpgrades;
	}

	public StandardLevelUpUpgrades getStandardLevelUpUpgrades(){
		return standardLevelUpUpgrades;
	}

	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public List<String> getTags(){
		return tags;
	}

	public void setPurchaseBonuses(PurchaseBonuses purchaseBonuses){
		this.purchaseBonuses = purchaseBonuses;
	}

	public PurchaseBonuses getPurchaseBonuses(){
		return purchaseBonuses;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPlayerSelectable(boolean playerSelectable){
		this.playerSelectable = playerSelectable;
	}

	public boolean isPlayerSelectable(){
		return playerSelectable;
	}

	public void setNeedsTesting(boolean needsTesting){
		this.needsTesting = needsTesting;
	}

	public boolean isNeedsTesting(){
		return needsTesting;
	}

	public void setHideoutRichPresence(String hideoutRichPresence){
		this.hideoutRichPresence = hideoutRichPresence;
	}

	public String getHideoutRichPresence(){
		return hideoutRichPresence;
	}

	public void setItems(Items items){
		this.items = items;
	}

	public Items getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"complexity = '" + complexity + '\'' + 
			",gender = '" + gender + '\'' + 
			",skin = '" + skin + '\'' + 
			",description = '" + description + '\'' + 
			",prerelease_only = '" + prereleaseOnly + '\'' + 
			",colors = '" + colors + '\'' + 
			",item_draft_bucketing = '" + itemDraftBucketing + '\'' + 
			",popular_items = '" + popularItems + '\'' + 
			",shop_stat_display = '" + shopStatDisplay + '\'' + 
			",physics = '" + physics + '\'' + 
			",gun_tag = '" + gunTag + '\'' + 
			",disabled = '" + disabled + '\'' + 
			",hero_type = '" + heroType + '\'' + 
			",level_info = '" + levelInfo + '\'' + 
			",id = '" + id + '\'' + 
			",search_name = '" + searchName + '\'' + 
			",class_name = '" + className + '\'' + 
			",stats_display = '" + statsDisplay + '\'' + 
			",item_slot_info = '" + itemSlotInfo + '\'' + 
			",assigned_players_only = '" + assignedPlayersOnly + '\'' + 
			",limited_testing = '" + limitedTesting + '\'' + 
			",models = '" + models + '\'' + 
			",images = '" + images + '\'' + 
			",cost_bonuses = '" + costBonuses + '\'' + 
			",hero_stats_ui = '" + heroStatsUi + '\'' + 
			",scaling_stats = '" + scalingStats + '\'' + 
			",starting_stats = '" + startingStats + '\'' + 
			",development_state = '" + developmentState + '\'' + 
			",in_development = '" + inDevelopment + '\'' + 
			",standard_level_up_upgrades = '" + standardLevelUpUpgrades + '\'' + 
			",tags = '" + tags + '\'' + 
			",purchase_bonuses = '" + purchaseBonuses + '\'' + 
			",name = '" + name + '\'' + 
			",player_selectable = '" + playerSelectable + '\'' + 
			",needs_testing = '" + needsTesting + '\'' + 
			",hideout_rich_presence = '" + hideoutRichPresence + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}