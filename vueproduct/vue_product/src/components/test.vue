<template>
    <div>
        <h2 class="title">{{msg}}</h2>
        <h3>{{$store.state.count}}</h3>

        <result></result>
        <opbutton></opbutton>
    </div>

</template>


<script>
    import store from "../store/store.js";
    import { mapState,mapMutations,mapGetters,mapActions } from 'vuex';
    export default {
        data(){
            return{
                msg:'Hello Vuex',
            }
        },
        store,
        components: {
            result: {
                template: `<div>{{count}}</div>`,
                //如何在Vue组件中获取Vuex的状态值？
                //1.通过computed属性直接给对象赋值
                // computed: {
                //     count() {
                //         return this.$store.state.count * 3;
                //     }
                // } 
                //2.通过 mapState辅助函数 
                // computed:mapState({
                //     count: state => state.count * 3
                // })
                //3.当映射的计算属性的名称与state子节点名称相同的时候，可以使用mapState传递一个数组 
                // computed: mapState(['count'])
                //4.
                computed:{
                    ...mapGetters(["count"])
                }
            },
            opbutton: {
                // template:`<div>
                //             <input type="button" value="+" @click="$store.commit('add')"/>
                //             <input type="button" value="-" @click="$store.commit('mul')"/>
                //         </div>`,
                // template: `
                //         <div>
                //             <input type="button" value="+" @click="add"/>
                //             <input type="button" value="-" @click="mul"/>
                //         </div> `,
                       
                template: `
                        <div>
                            <input type="button" value="+" @click="addAction"/>
                            <input type="button" value="-" @click="mulAction"/>
                        </div>
                        `
                ,
                methods:{
                    ...mapMutations(['add','mul']),
                    ...mapActions(['addAction','mulAction'])
                }
            }
        }

    }
</script>

<style scoped>
*{margin: 0;padding: 0}
.title{
    text-align: center;
    height:49px;
    line-height:49px;
    border-bottom:1px solid #ccc;
    background: #fff;
}

</style>