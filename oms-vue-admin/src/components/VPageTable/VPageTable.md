# PageTable

> 这是一个对element-ui二次扩展的一个表格分页组件。

# 使用步骤

>- 1 在需要使用的vue页面script标签前导入组件，命令如下：

    import PageTable from '~/components/PageTable'
    
>- 2 注入组件并使用
 
        注入组件 components: {PageTable}
        使用组件 <page-table :tableData="tableData" :tableColumn="tableColumn"></page-table>
        

 #组件属性
#### 1.Table属性
  >- ##### tableData 渲染table列表的数据数组
  >- ##### 
  
size	尺寸	string	medium / small / mini	—
type	类型	string	primary / success / warning / danger / info / text	—
plain	是否朴素按钮	boolean	—	false
round	是否圆角按钮	boolean	—	false
circle	是否圆形按钮	boolean	—	false
loading	是否加载中状态	boolean	—	false
disabled	是否禁用状态	boolean	—	false
icon	图标类名	string	—	—
autofocus	是否默认聚焦	boolean	—	false
native-type	原生 type 属性	string	button / submit / reset	button
