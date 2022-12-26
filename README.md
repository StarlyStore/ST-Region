# ST-Region

### API Usage
##### 구역 생성
```java
RegionAPI api = new RegionAPI();

api.createRegion("regionName", 
        new Location(Bukkit.getWorld("world"), 0, 0, 0), 
        new Location(Bukkit.getWorld("world"), 0, 1, 0));
```

##### 구역 불러오기
```java
RegionAPI api = new RegionAPI();

api.getRegion("regionName");
```

##### 구역 삭제
```java
RegionAPI api = new RegionAPI();

api.deleteRegion("regionName");
```

---

##### Pos1 설정
```java
RegionAPI api = new RegionAPI();

api.setPos1(Bukkit.getPlayer("Starly"), new Location(Bukkit.getWorld("world"), 0, 0, 0));
```
##### Pos1 불러오기
```java
RegionAPI api = new RegionAPI();

api.getPos1(Bukkit.getPlayer("Starly"));
```

##### Pos2 설정
```java
RegionAPI api = new RegionAPI();

api.setPos2(Bukkit.getPlayer("Starly"), new Location(Bukkit.getWorld("world"), 0, 0, 0));
```

##### Pos2 불러오기
```java
RegionAPI api = new RegionAPI();

api.getPos2(Bukkit.getPlayer("Starly"));
```