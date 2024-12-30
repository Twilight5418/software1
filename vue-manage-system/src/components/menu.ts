import { Menus } from '@/types/menu';

export const menuData: Menus[] = [
    {
        id: '0',
        title: '系统首页',
        index: '/dashboard',
        icon: 'Odometer',
    },
    {
        id: '900',
        title: '申请课题',
        index: '/course-request',
        icon: 'Odometer',
    },
    {
        id: '901',
        title: '课题审核',
        index: '/course-review',
        icon: 'Odometer',
    },
    {
        id: '902',
        title: '学生选题',
        index: '/course-select',
        icon: 'Odometer',
    },
    {
        id: '1',
        title: '系统管理',
        index: '1',
        icon: 'HomeFilled',
        children: [
            {
                id: '101',
                pid: '1',
                index: '/default-reason-management',
                title: '违约原因维护',
            },
            {
                id: '102',
                pid: '1',
                index: '/default-report-review',
                title: '违约申请审核',
            },
            {
                id: '103',
                pid: '1',
                index: '/default-respawn',
                title: '违约重生',
            },
            {
                id: '104',
                pid: '1',
                index: '/default-respawn-review',
                title: '违约重生审核',
            },
            {
                id: '105',
                pid: '1',
                index: '/default-search',
                title: '违约记录查询',
            },
        ],
    },
    
    {
        id: '2',
        title: '组件',
        index: '2-1',
        icon: 'Calendar',
        children: [
            {
                id: '21',
                pid: '3',
                index: '/form',
                title: '表单',
            },
            {
                id: '22',
                pid: '3',
                index: '/upload',
                title: '上传',
            },
            {
                id: '23',
                pid: '2',
                index: '/carousel',
                title: '走马灯',
            },
            {
                id: '24',
                pid: '2',
                index: '/calendar',
                title: '日历',
            },
            {
                id: '25',
                pid: '2',
                index: '/watermark',
                title: '水印',
            },
            {
                id: '26',
                pid: '2',
                index: '/tour',
                title: '分布引导',
            },
            {
                id: '27',
                pid: '2',
                index: '/steps',
                title: '步骤条',
            },
            {
                id: '28',
                pid: '2',
                index: '/statistic',
                title: '统计',
            },
            {
                id: '29',
                pid: '3',
                index: '29',
                title: '三级菜单',
                children: [
                    {
                        id: '291',
                        pid: '29',
                        index: '/editor',
                        title: '富文本编辑器',
                    },
                    {
                        id: '292',
                        pid: '29',
                        index: '/markdown',
                        title: 'markdown编辑器',
                    },
                ],
            },
        ],
    },
    // {
    //     id: '5',
    //     title: '表格',
    //     index: '3',
    //     icon: 'Calendar',
    //     children: [
    //         {
    //             id: '105',
    //             pid: '1',
    //             index: '/default-search',
    //             title: '违约记录查询',
    //         },
    //     ],
    // },
    {
        id: '4',
        icon: 'PieChart',
        index: '4',
        title: '图表',
        children: [
            {
                id: '41',
                pid: '4',
                index: '/schart',
                title: 'schart图表',
            },
            {
                id: '42',
                pid: '4',
                index: '/echarts',
                title: 'echarts图表',
            },
        ],
    },
    {
        id: '5',
        icon: 'Guide',
        index: '/default-search',
        title: '违约记录查询',
        permiss: '105',
    },
    {
        id: '7',
        icon: 'Brush',
        index: '/theme',
        title: '主题',
    },
    {
        id: '6',
        icon: 'DocumentAdd',
        index: '6',
        title: '附加页面',
        children: [
            {
                id: '61',
                pid: '6',
                index: '/ucenter',
                title: '个人中心',
            },
            {
                id: '62',
                pid: '6',
                index: '/login',
                title: '登录',
            },
            {
                id: '63',
                pid: '6',
                index: '/register',
                title: '注册',
            },
            {
                id: '64',
                pid: '6',
                index: '/reset-pwd',
                title: '重设密码',
            },
            {
                id: '65',
                pid: '6',
                index: '/403',
                title: '403',
            },
            {
                id: '66',
                pid: '6',
                index: '/404',
                title: '404',
            },
        ],
    },
    {
        id: '100',
        icon: 'Setting',
        index: '/default-report',
        title: '违约认定',
    },
];