目前尝试实现的目标：

服务器端 

    两个button 启动服务器和关闭服务器
    点击启动服务器后启动socket 并且弹出提示窗口“Server is on run”
    点击关闭服务器后关闭程序 并且弹出提示窗口"Server shutdown"
    每当有客户端连接时，弹出提示窗口“A new client has connected”
    服务器端无收发消息功能（仅输送IO流）

客户端

    Stage1
    一个TextField 输入用户名称 一个button 连接至服务器
    点击连接至服务器后连接socket 并且弹出提示窗口"Connected to server"
    
    Stage2
    Stage2的窗口名称为用户名称
    一个ScrollPane，上着一个Vbox，用于生成可滚动的Hbox；一个TextField，一个button 发送
    目前好像只能先尝试实现不显示用户名，只有聊天内容的聊天室
