#月卡插件

> 由 ISOTOPE Studio 制作

主要用于RMB玩家

##GUI
GUI尽量可以使用NBT

首先指令/yueka  打开一个GUI（名：月卡）

物品ID（配置文件自定义）
    
第1物品设置自定义信息（用来我配置月卡介绍自定义信息）

第3物品设置购买月卡（打开权限的GUI选择购买的月卡价格自定义（权限yueka.xxx玩家没有默认权限OP默认也没有（不同权限获得不同奖励那样）点卷是ca插件购买应该会检测一下够不够点卷）
                                          
第5物品设置签到（每天签到12点重置（当玩家点击签到的时候根据他的权限来给予多少个奖励（后台指令）））

第8物品 关闭

###自定义物品
 示例物品：
```YAML
test:
  物品ID，此处为石头(id=1)
  Id: 1
  物品数量
  Amount: 1
  物品显示的名字
  Name: '&b测试物品'
  物品Lore
  Lore:
 - '&e这是一个&a测试物品&e~~~'
```

##点击签到
获得指令奖励

每日一次

##月卡价格
          权限   点卷金额
例子：yueka.1|5000 即为公会从3级升到4级需要5000公会资金
```YAML
ExtUpgradePrice:
  - 'yueka.1|1000'
  - 'yueka.2|5000'
  - 'yueka.3|10000'
  - 'yueka.4|20000'
  - 'yueka.5|40000'
  - 'yueka.6|80000'
```