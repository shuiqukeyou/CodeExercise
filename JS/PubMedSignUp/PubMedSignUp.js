// ����Ϊ��һ������Ҫ���Ƶ�����

function setSelected(selectObj,optionsText){
    for(var i=0;i<selectObj.options.length;i++){
        if(selectObj.options[i].text==optionsText){
            selectObj.options[i].selected=true;
            return i;
        }
    }
}

void((function(){
// ��������ҳ����Ҫ�ֶ������ֵ���밴ʵ������޸�textValueList�е�ֵ����Ҫ��textNameList
var textNameList = new Array("xmpy","txdz","yzbm","lxdh","dzxx","zcxh")

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######
var textValueList = new Array("����ƴ��","����ͨѶ��ַ","�ʱ�","�̶��绰","��������","ע��ѧ��")
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######

for (var i=0;i<textNameList.length;i++){
	document.getElementById(textNameList[i]).value = textValueList[i];
}



// ������ҳ����Ҫ�����ѡ�Ĳ��֣���������ֵ���Լ�ȥ����ҳԴ�룬����ֻ�ṩһ����
// �Ա�xbm_0���У���ΪŮ�� xbm_0 ��Ϊ xbm_1
// ����״����hfm_0��δ�飬���ѻ齫 hfm_0 ��Ϊ hfm_1��ɥż֮�������ѡ����ȥ����ҳԴ��
// Ĭ��Ϊ��xbm_0��hfm_0  �С�δ��
var clickNameList = new Array("xbm_0","hfm_0")
for (var i=0;i<clickNameList.length;i++){
	document.getElementById(clickNameList[i]).click();
}

// ������ҳ����Ҫ����ѡ��Ĳ��֣��밴ʵ�������һ�ֲ�©�ġ��޸�textValueList�е�ֵ����Ϊ�ǰ�ֵƥ���
// ����������ò��Ҫдȫ���й�����������������Ա��д��Ա��ƥ�䲻�˵�
// ��Ҫ��textNameList
// û����Է�Ӧ���������ã���Ϊ����Ӧ��ģ����������޸�Դ����ߵȴ��ű�ִ����ɺ���ѡ
var selectNameList = new Array("xyjrm","mzm","zzmmm","kslym","xxxs","bklbm","bydwssm","byzydm")

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######
var selectValueList = new Array("���۾���","����","������ò","������Դ","ȡ�����ѧ����ѧϰ��ʽ","�������","��ѧ����ʡ��","רҵ����")// ������Ҫ�ֶ��޸�
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######

for (var i=0;i<selectNameList.length;i++){
	var selObj=document.getElementById(selectNameList[i]);
	setSelected(selObj,selectValueList[i]);
}

// ��ҳ�Դ��ĸ��º���
selectedKsly();
refreshBydwList();
loadByzy2();

// �ȴ���������ԺУ��רҵ����������������ñ����ӳ������趨��Ĭ��3�루���Ǹ�3000�����ӳ٣���λ���룩
// ԺУ��רҵҲ�������˵�����ͬ����Ҫ�ϸ�ƥ��
setTimeout(function(){ 
var selObj=document.getElementById("bydwm");

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######
setSelected(selObj,"(ѧУ���)ѧԺ����");
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######

var selObj=document.getElementById("byzydm2");

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######
setSelected(selObj,"רҵ���-רҵ����");
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######

selectedBydw() //ԭ���Դ���һ�����ã���֪����ʲô���ã����ÿ�
}, 3000);

})())

// ����Ϊ��һ������Ҫ���Ƶ�����









// ����Ϊ�ڶ�������Ҫ���Ƶ�����

function setSelected(selectObj,optionsText){
    for(var i=0;i<selectObj.options.length;i++){
        if(selectObj.options[i].text==optionsText){
            selectObj.options[i].selected=true;
            return i;
        }
    }
}

void((function(){
// ��������ҳ����Ҫ�ֶ������ֵ���밴ʵ������޸�textValueList�е�ֵ����Ҫ��textNameList
var textNameList = new Array("hkszdxxdz","xxgzdw","daszdw","daszdwdz","daszdwyzbm","jlcf","kszbqk","xingming0","guanxi0","danweizhiwu0","dianhua0","xingming1","guanxi1","danweizhiwu1","dianhua1","nianyue0","danwei0","zhiwu0")

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ���ܳ���######
var textValueList = new Array("���ڵ�ַ","ѧУ��ַ","�������ڵ�λ","�������ڵ�λ��ַ","�������ڵ�λ�ʱ�","���ͣ��ޣ�","���ף��ޣ�","������1","������ϵ1","������ְ��λ1","�����绰1","������2","������ϵ2","������ְ��λ2","�����绰2","��λ����ʱ�䣨2014��9����","��λ������ַ","ѧ��")
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ���ܳ���######

for (var i=0;i<textNameList.length;i++){
	document.getElementById(textNameList[i]).value = textValueList[i]
}

// ������ҳ����Ҫ����ѡ��Ĳ��֣��밴ʵ�������һ�ֲ�©�ġ��޸�textValueList�е�ֵ
// ��Ҫ��textNameList
var selectNameList = new Array("jgss","hkszdss","csdss","daszdss")

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######
var selectValueList = new Array("���ᣨʡ��","���ڣ�ʡ��","�����أ�ʡ��","����λ�ã�ʡ��")
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######

for (var i=0;i<selectNameList.length;i++){
	var selObj=document.getElementById(selectNameList[i]);
	setSelected(selObj,selectValueList[i]);
}

//��ҳ�Դ��ĸ��º���
updateJgDjsList()
updateHkszdDjsList();
updateCsdDjsList();
updateDaszdDjsList();

// �ȴ��������س��к����ؽ�����������ñ����ӳ������趨��Ĭ�϶���0.5��
setTimeout(function(){ 
// ������ҳ����Ҫ����ѡ��Ĳ��֣��밴ʵ�������һ�ֲ�©�ġ��޸�textValueList�е�ֵ
// ��Ҫ��textNameList
var selectNameList = new Array("jgdjs","hkszddjs","csddjs","daszddjs")

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######
var selectValueList = new Array("���ᣨ�У�","���ڣ��У�","�����أ��У�","����λ�ã��У�")
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######

for (var i=0;i<selectNameList.length;i++){
	var selObj=document.getElementById(selectNameList[i]);
	setSelected(selObj,selectValueList[i]);
	//��ҳ�Դ��ĸ��º���
	updateJgXjsList();
	updateHkszdXjsList();
	updateCsdXjsList();
	updateDaszdXjsList();
}}, 500);



setTimeout(function(){ 
// ������ҳ����Ҫ����ѡ��Ĳ��֣��밴ʵ�������һ�ֲ�©�ġ��޸�textValueList�е�ֵ
// ��Ҫ��textNameList
var selectNameList = new Array("jgszdm","hkszdm","csdm","daszdm")

// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######
var selectValueList = new Array("���ᣨ��/�أ�","���ڣ���/�أ�","�����أ���/�أ�","����λ�ã���/�أ�")
// ######����ע���еĴ�����Ҫ�ֶ��޸����������ֵ######

for (var i=0;i<selectNameList.length;i++){
	var selObj=document.getElementById(selectNameList[i]);
	setSelected(selObj,selectValueList[i]);
}}, 1000);

})())

// ����Ϊ�ڶ�������Ҫ���Ƶ�����
//��ʽ�������ڵģ��ü��±�д���и����ӵĸ�ʽ