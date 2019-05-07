function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%
X = [ones(m,1) X]; % 添加输入层偏置值

temp_h = sigmoid(X * Theta1'); % 第一层输出
temp_h = [ones(m,1) temp_h]; % 添加第二层偏置值
h = sigmoid(temp_h * Theta2'); % 获取全部输出(5000*10)

y_matric = zeros(m, num_labels); % 5000*10
for i = 1:m % 遍历所有数据集
  y_matric(i,y(i)) = 1; % 将输出向量的第1行的对应输出位调整为1
endfor

temp = y_matric .* log(h) + (1-y_matric) .* log(1-h); % 求成本矩阵
J = -sum(sum(temp))/m; % 求和

Theta1_temp = Theta1(:,2:input_layer_size+1); % 去除偏置单元的Theta
Theta2_temp = Theta2(:,2:hidden_layer_size+1); % 去除偏置单元的Theta
J_lambda = lambda/ (2*m) * (sum(sum(Theta1_temp.^2)) + sum(sum(Theta2_temp.^2)));% 正规项成本

J = J + J_lambda; % 成本计算完毕

Delta_1 = zeros(size(Theta1));
Delta_2 = zeros(size(Theta2));


for i = 1:m
  a1 = X(i,:)'; % 取出1个x
  
  z2 = Theta1 * a1; % 计算第二层的值
  a2 = sigmoid(z2); % 计算第二层的输出值
  
  a2 = [1;a2]; % 添加偏置层
  
  z3 = Theta2 * a2; % 计算最终值
  a3 = sigmoid(z3); % 计算输出

  delta_3 = a3 - y_matric(i,:)'; % 求最后一层的误差
  
  delta_2 = Theta2' * delta_3;
  delta_2 = delta_2(2:end); % 删除对偏置项的修改
  delta_2 = delta_2 .* sigmoidGradient(z2); % 求隐藏层的误差，输入层不求
  
  
  Delta_2 = Delta_2 + delta_3 * a2'; % 计算第二层的偏导
  Delta_1 = Delta_1 + delta_2 * a1'; % 计算第一层的偏导
endfor
Theta1(:,1) = 0;
Theta2(:,1) = 0;
Theta1_grad = (Delta_1 + lambda * Theta1)/m;
Theta2_grad = (Delta_2 + lambda * Theta2)/m;
%Theta1_grad = Delta_1/m;
%Theta2_grad = Delta_2/m;





% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
