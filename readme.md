Controller层     不多说
Service层        不多说
Dao层            和数据库交互使用，因为一直在用jpa，先用jpa写着，需要的话可以换mybatis（感觉没有复杂的查询jpa够用了）
Entity层         存储需要保存再数据库中的表
Model层          返回前端用的组件类，放着不管就行
Util层           存各种可以单独的组件，很多业务的处理写util更简洁些
Filter层         当初学完springboot就没用过过滤器了，得重学QAQ