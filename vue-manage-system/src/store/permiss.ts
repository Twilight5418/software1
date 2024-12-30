import { defineStore } from 'pinia';

interface ObjectList {
    [key: string]: string[];
}

export const usePermissStore = defineStore('permiss', {
    state: () => {
        const defaultList: ObjectList = {
            manager: [
                '0',
                '901',
        
            ],
            user: ['0', '100','5','105'],
            student:['0','902'],
            teacher:['0','900'],
        };
        const userrole = localStorage.getItem('role');
      
        return {
            key:  (userrole ? defaultList[userrole] : defaultList.user),
            defaultList,
        };
    },
    actions: {
        handleSet(val: string[]) {
            this.key = val;
        },
    },
});
